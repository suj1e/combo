package org.flooc.combox.autoconfigure.boot.security;

import feign.RequestInterceptor;
import org.flooc.combox.boot.security.config.SecurityConfiguration;
import org.flooc.combox.boot.security.config.SecurityFilterChainConfiguration;
import org.flooc.combox.cloud.openfeign.UserFeignClientInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * @author sujie
 * @since 1.0.0
 */
@AutoConfiguration
@ConditionalOnClass(HttpSecurity.class)
public class SecurityAutoConfiguration {

  @Import({SecurityConfiguration.class, SecurityFilterChainConfiguration.class})
  static class EnableSecurityConfig {

  }

  @Configuration(proxyBeanMethods = false)
  @ConditionalOnClass(RequestInterceptor.class)
  static class EnableSecurityOpenfeignConfig {

    @Bean
    public RequestInterceptor userFeignRequestInterceptor() {
      return new UserFeignClientInterceptor();
    }
  }


}
