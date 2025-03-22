package org.flooc.combo.x.security.config;

import feign.RequestInterceptor;
import org.flooc.combo.x.security.common.SecurityAuthenticationEntryPoint;
import org.flooc.combo.x.security.provider.admin.PasswordAdminAuthenticationProvider;
import org.flooc.combo.x.security.provider.admin.SmsAdminAuthenticationProvider;
import org.flooc.combo.x.security.support.SecurityAuditorAware;
import org.flooc.combo.x.security.support.UserFeignClientInterceptor;
import org.flooc.combo.x.security.support.svc.account.admin.AdminAccountAuthCmdSvc;
import org.flooc.combo.x.security.support.svc.account.admin.AdminAccountAuthQrySvc;
import org.flooc.combo.x.security.support.svc.account.admin.impl.NullAdminAccountAuthCmdSvcImpl;
import org.flooc.combo.x.security.support.svc.account.admin.impl.NullAdminAccountAuthQrySvcImpl;
import org.flooc.combo.x.sms.check.SmsVerifyCodeChecker;
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
	@ConditionalOnMissingBean(AdminAccountAuthQrySvc.class)
	public AdminAccountAuthQrySvc adminAccountAuthQrySvc() {
		return new NullAdminAccountAuthQrySvcImpl();
	}

	@Bean
	@ConditionalOnMissingBean(AdminAccountAuthCmdSvc.class)
	public AdminAccountAuthCmdSvc adminAccountAuthCmdSvc() {
		return new NullAdminAccountAuthCmdSvcImpl();
	}

	@Bean
	public SmsAdminAuthenticationProvider smsAdminAuthenticationProvider(SmsVerifyCodeChecker smsVerifyCodeChecker,
			AdminAccountAuthQrySvc adminAccountAuthQrySvc, AdminAccountAuthCmdSvc adminAccountAuthCmdSvc) {
		return new SmsAdminAuthenticationProvider(smsVerifyCodeChecker, adminAccountAuthQrySvc, adminAccountAuthCmdSvc);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public PasswordAdminAuthenticationProvider passwordAdminAuthenticationProvider(PasswordEncoder passwordEncoder,
			AdminAccountAuthQrySvc adminAccountAuthQrySvc) {
		return new PasswordAdminAuthenticationProvider(passwordEncoder, adminAccountAuthQrySvc);
	}

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new SecurityAuthenticationEntryPoint();
	}

	@Bean
	@ConditionalOnClass(AuditorAware.class)
	public SecurityAuditorAware securityAuditorAware() {
		return new SecurityAuditorAware();
	}

	@Bean
	@ConditionalOnClass(RequestInterceptor.class)
	public RequestInterceptor userFeignRequestInterceptor() {
		return new UserFeignClientInterceptor();
	}

}
