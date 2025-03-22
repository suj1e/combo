package org.flooc.combo.x.sms;

import java.util.List;
import org.flooc.combo.x.sms.check.SmsVerifyCodeChecker;
import org.flooc.combo.x.sms.check.SmsVerifyCodeCheckerImpl;
import org.flooc.combo.x.sms.support.ISmsSendService;
import org.flooc.combo.x.sms.support.SmsBizType;
import org.flooc.combo.x.sms.support.SmsMessageProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
  @ConditionalOnMissingBean(SmsVerifyCodeChecker.class)
  public SmsVerifyCodeChecker verifyCodeChecker(StringRedisTemplate stringRedisTemplate) {
    return new SmsVerifyCodeCheckerImpl(stringRedisTemplate);
  }

  @Bean
  @ConditionalOnClass(StringRedisTemplate.class)
  public SmsCodeListener smsCodeListener(StringRedisTemplate redisTemplate) {
    return new SmsCodeListener(redisTemplate);
  }

  @Bean
  @ConditionalOnMissingBean
  public PluginRegistry<ISmsSendService, SmsBizType> smsPluginRegistry(List<ISmsSendService> smsSendServices) {
    return SimplePluginRegistry.of(smsSendServices);
  }

}
