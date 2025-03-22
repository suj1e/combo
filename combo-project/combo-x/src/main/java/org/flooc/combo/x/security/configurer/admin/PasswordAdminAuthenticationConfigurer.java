package org.flooc.combo.x.security.configurer.admin;

import org.flooc.combo.x.security.config.JwtProperties;
import org.flooc.combo.x.security.filter.admin.PasswordAdminAuthenticationFilter;
import org.flooc.combo.x.security.handler.admin.PasswordAdminAuthenticationFailureHandler;
import org.flooc.combo.x.security.handler.admin.PasswordAdminAuthenticationSuccessHandler;
import org.flooc.combo.x.security.provider.admin.PasswordAdminAuthenticationProvider;
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
public final class PasswordAdminAuthenticationConfigurer<H extends HttpSecurityBuilder<H>>
		extends AbstractHttpConfigurer<PasswordAdminAuthenticationConfigurer<H>, H> {

	@Override
	public void init(H http) throws Exception {
		http.setSharedObject(PasswordAdminAuthenticationSuccessHandler.class,
				new PasswordAdminAuthenticationSuccessHandler());
		http.setSharedObject(PasswordAdminAuthenticationFailureHandler.class,
				new PasswordAdminAuthenticationFailureHandler());
		super.init(http);
	}

	@Override
	public void configure(H http) throws Exception {
		ApplicationContext applicationContext = http.getSharedObject(ApplicationContext.class);
		PasswordAdminAuthenticationSuccessHandler successHandler = http
			.getSharedObject(PasswordAdminAuthenticationSuccessHandler.class);
		JwtProperties jwtProperties = applicationContext.getBean(JwtProperties.class);
		successHandler.setJwtProperties(jwtProperties);
		PasswordAdminAuthenticationFailureHandler failureHandler = http
			.getSharedObject(PasswordAdminAuthenticationFailureHandler.class);
		AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		builder.authenticationProvider(applicationContext.getBean(PasswordAdminAuthenticationProvider.class));
		PasswordAdminAuthenticationFilter filter = new PasswordAdminAuthenticationFilter(builder.getObject(),
				successHandler, failureHandler);
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		super.configure(http);
	}

}
