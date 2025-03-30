package org.flooc.combox.boot.security.config;

import org.flooc.combo.security.common.SecurityAuthenticationEntryPoint;
import org.flooc.combo.security.jwt.JwtWriter;
import org.flooc.combo.sms.verifycode.check.IVerifyCodeService;
import org.flooc.combox.boot.dataoperation.config.DataOperationProperties;
import org.flooc.combox.boot.dataoperation.config.SecurityAuditorAware;
import org.flooc.combox.boot.security.account.service.AdminAccountAuthCmdService;
import org.flooc.combox.boot.security.account.service.AdminAccountAuthQryService;
import org.flooc.combox.boot.security.account.service.impl.NullAdminAccountAuthCmdServiceImpl;
import org.flooc.combox.boot.security.account.service.impl.NullAdminAccountAuthQryServiceImpl;
import org.flooc.combox.boot.security.admin.password.PasswordAdminAuthenticationProvider;
import org.flooc.combox.boot.security.admin.sms.SmsAdminAuthenticationProvider;
import org.flooc.combox.boot.security.jwt.DefaultJwtWriter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author sujie
 * @since 1.0.0
 */
@Configuration(proxyBeanMethods = false)
public class SecurityConfiguration {


  @Bean
  @ConditionalOnMissingBean
  public AdminAccountAuthQryService adminAccountAuthQrySvc() {
    return new NullAdminAccountAuthQryServiceImpl();
  }

  @Bean
  @ConditionalOnMissingBean
  public AdminAccountAuthCmdService adminAccountAuthCmdSvc() {
    return new NullAdminAccountAuthCmdServiceImpl();
  }

  @Bean
  @ConditionalOnMissingBean
  public JwtWriter jwtWriter(JwtProperties jwtProperties) {
    return new DefaultJwtWriter(jwtProperties);
  }

  @Bean
  public SmsAdminAuthenticationProvider smsAdminAuthenticationProvider(
      IVerifyCodeService iVerifyCodeService,
      AdminAccountAuthQryService adminAccountAuthQryService,
      AdminAccountAuthCmdService adminAccountAuthCmdService) {
    return new SmsAdminAuthenticationProvider(iVerifyCodeService, adminAccountAuthQryService,
        adminAccountAuthCmdService);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public PasswordAdminAuthenticationProvider passwordAdminAuthenticationProvider(
      PasswordEncoder passwordEncoder,
      AdminAccountAuthQryService adminAccountAuthQryService) {
    return new PasswordAdminAuthenticationProvider(passwordEncoder, adminAccountAuthQryService);
  }

  @Bean
  public AuthenticationEntryPoint authenticationEntryPoint() {
    return new SecurityAuthenticationEntryPoint();
  }

  @Bean
  @ConditionalOnClass(AuditorAware.class)
  public SecurityAuditorAware securityAuditorAware(
      DataOperationProperties dataOperationProperties) {
    return new SecurityAuditorAware(dataOperationProperties.getDefaultSecurityAuditor());
  }


}
