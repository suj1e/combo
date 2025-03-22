package org.flooc.combo.x.security.configurer.admin;

import org.flooc.combo.x.security.config.JwtProperties;
import org.flooc.combo.x.security.filter.admin.SmsAdminAuthenticationFilter;
import org.flooc.combo.x.security.handler.admin.SmsAdminAuthenticationFailureHandler;
import org.flooc.combo.x.security.handler.admin.SmsAdminAuthenticationSuccessHandler;
import org.flooc.combo.x.security.provider.admin.SmsAdminAuthenticationProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * <p>
 * 主要就是对此security filter chain的组件初始化和配置，一个configurer相当于一个门 1. 添加和初始化此类型security
 * filter对应的过滤器，同时初始化chain的sharedObject 共享组件 2. 配置successHandler, failureHandler,
 * authenticationManager 3. 注册filter
 * </p>
 *
 * @author sujie
 * @since 1.0.0
 */
public final class SmsAdminAuthenticationConfigurer<H extends HttpSecurityBuilder<H>>
		extends AbstractHttpConfigurer<SmsAdminAuthenticationConfigurer<H>, H> {

	@Override
	public void init(H http) throws Exception {
		http.setSharedObject(SmsAdminAuthenticationSuccessHandler.class, new SmsAdminAuthenticationSuccessHandler());
		http.setSharedObject(SmsAdminAuthenticationFailureHandler.class, new SmsAdminAuthenticationFailureHandler());
		super.init(http);
	}

	@Override
	public void configure(H http) throws Exception {
		ApplicationContext applicationContext = http.getSharedObject(ApplicationContext.class);
		SmsAdminAuthenticationSuccessHandler successHandler = http
			.getSharedObject(SmsAdminAuthenticationSuccessHandler.class);
		JwtProperties jwtProperties = applicationContext.getBean(JwtProperties.class);
		successHandler.setJwtProperties(jwtProperties);
		SmsAdminAuthenticationFailureHandler failureHandler = http
			.getSharedObject(SmsAdminAuthenticationFailureHandler.class);
		AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		builder.authenticationProvider(applicationContext.getBean(SmsAdminAuthenticationProvider.class));
		SmsAdminAuthenticationFilter filter = new SmsAdminAuthenticationFilter(builder.getObject(), successHandler,
				failureHandler);
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		super.configure(http);
	}

}
