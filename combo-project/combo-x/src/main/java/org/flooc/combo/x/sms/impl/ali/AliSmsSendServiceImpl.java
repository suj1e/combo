package org.flooc.combo.x.sms.impl.ali;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import darabonba.core.client.ClientOverrideConfiguration;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flooc.combo.common.exception.CmpException;
import org.flooc.combo.x.constant.CmpExceptionConstant.SmsExceptionEnum;
import org.flooc.combo.x.sms.SmsCodeEvent;
import org.flooc.combo.x.sms.SmsCodeTemplate;
import org.flooc.combo.x.sms.check.SmsCodeCheckModel;
import org.flooc.combo.x.sms.support.ISmsSendService;
import org.flooc.combo.x.sms.support.SmsBizType;
import org.flooc.combo.x.sms.support.SmsSendModel;
import org.flooc.combo.x.web.startup.SpringAppUtil;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

/**
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
public class AliSmsSendServiceImpl implements ISmsSendService {

  private final AliyunSmsProperties aliyunSmsProperties;

  @Override
  public boolean sendSms(SmsSendModel model) {
    SmsCodeTemplate codeTemplate = new SmsCodeTemplate(
        aliyunSmsProperties.getSms().getExpiredTime());
    ObjectMapper om = new ObjectMapper();
    String msg;
    try {
      msg = om.writeValueAsString(codeTemplate);
    } catch (JsonProcessingException e) {
      throw new CmpException(SmsExceptionEnum.SmsSendError, e);
    }
    String phone = model.getPhones().getFirst();
    SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
        .signName(aliyunSmsProperties.getSms().getSignName())
        .templateCode(aliyunSmsProperties.getSms().getTemplateCode())
        .phoneNumbers(phone)
        .templateParam(msg)
        .build();

    SendSmsResponse resp;
    try {
      resp = send(sendSmsRequest);
    } catch (Exception e) {
      throw new CmpException(SmsExceptionEnum.SmsSendError, e);
    }
    Integer statusCode = resp.getStatusCode();
    SendSmsResponseBody respBody = resp.getBody();
    String code = respBody.getCode();
    if (HttpStatus.OK.value() == statusCode && HttpStatus.OK.name().equalsIgnoreCase(code)) {
      SpringAppUtil.publishEvent(new SmsCodeEvent(new SmsCodeCheckModel(phone, codeTemplate)));
      if (log.isDebugEnabled()) {
        log.debug("Sending has been performed, resp: bizId:{}, code:{}, msg:{}",
            respBody.getBizId(), code,
            respBody.getMessage());
      }
    } else {
      log.error("Failed to send sms, error: {}", respBody.getMessage());
      throw new CmpException(SmsExceptionEnum.SmsSendError);
    }
    return false;
  }

  @Override
  public boolean supports(@NonNull SmsBizType delimiter) {
    return SmsBizType.ALI.equals(delimiter);
  }

  private SendSmsResponse send(SendSmsRequest request)
      throws ExecutionException, InterruptedException {
    StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
        .accessKeyId(aliyunSmsProperties.getAccessKeyId())
        .accessKeySecret(aliyunSmsProperties.getAccessKeySecret())
        .build());

    AsyncClient client = AsyncClient.builder()
        .region(aliyunSmsProperties.getSms().getRegion())
        .credentialsProvider(provider)
        .overrideConfiguration(ClientOverrideConfiguration.create()
            .setEndpointOverride(aliyunSmsProperties.getSms().getEndpoint())
            .setConnectTimeout(Duration.ofSeconds(30)))
        .build();
    SendSmsResponse resp;
    try {
      CompletableFuture<SendSmsResponse> response = client.sendSms(request);
      resp = response.get();
    } finally {
      client.close();
    }
    return resp;
  }

}
