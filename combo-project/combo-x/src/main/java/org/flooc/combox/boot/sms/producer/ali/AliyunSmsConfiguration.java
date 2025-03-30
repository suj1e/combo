package org.flooc.combox.boot.sms.producer.ali;

import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sujie
 * @since 1.0.0
 */
@ConditionalOnClass(AsyncClient.class)
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({AliyunSmsProperties.class })
public class AliyunSmsConfiguration {

	@Bean
	public AliSmsSendServiceImpl aliSmsSendService(AliyunSmsProperties aliyunSmsProperties) {
		return new AliSmsSendServiceImpl(aliyunSmsProperties);
	}


}
