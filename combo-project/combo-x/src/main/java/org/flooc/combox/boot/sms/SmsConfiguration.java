package org.flooc.combox.boot.sms;

import java.util.List;
import org.flooc.combo.sms.ISmsSendService;
import org.flooc.combo.sms.SmsCodeEventProcessor;
import org.flooc.combo.sms.SmsProducerType;
import org.flooc.combo.sms.verifycode.check.IVerifyCodeService;
import org.flooc.combo.sms.verifycode.check.VerifyCodeServiceImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.plugin.core.SimplePluginRegistry;

/**
 * @author sujie
 * @since 1.0.0
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({SmsMessageProperties.class})
public class SmsConfiguration {

  @Bean
  public IVerifyCodeService verifyCodeService(StringRedisTemplate stringRedisTemplate) {
    return new VerifyCodeServiceImpl(stringRedisTemplate);
  }

  @Bean
  public SmsCodeEventProcessor smsCodeListener(StringRedisTemplate stringRedisTemplate) {
    return new SmsCodeEventProcessor(stringRedisTemplate);
  }

  @Bean(ISmsSendService.PLUGIN_BEAN_NAME)
  public PluginRegistry<ISmsSendService, SmsProducerType> smsPluginRegistry(
      List<ISmsSendService> smsSendServices) {
    return SimplePluginRegistry.of(smsSendServices);
  }

}
