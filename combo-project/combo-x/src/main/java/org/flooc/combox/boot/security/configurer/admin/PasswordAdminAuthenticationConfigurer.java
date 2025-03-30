package org.flooc.combox.boot.security.configurer.admin;

import org.flooc.combo.security.jwt.JwtWriter;
import org.flooc.combox.boot.security.admin.password.PasswordAdminAuthenticationFailureHandler;
import org.flooc.combox.boot.security.admin.password.PasswordAdminAuthenticationFilter;
import org.flooc.combox.boot.security.admin.password.PasswordAdminAuthenticationProvider;
import org.flooc.combox.boot.security.admin.password.PasswordAdminAuthenticationSuccessHandler;
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
		ApplicationContext applicationContext = http.getSharedObject(ApplicationContext.class);
		http.setSharedObject(PasswordAdminAuthenticationSuccessHandler.class,
				new PasswordAdminAuthenticationSuccessHandler(applicationContext.getBean(JwtWriter.class)));
		http.setSharedObject(PasswordAdminAuthenticationFailureHandler.class,
				new PasswordAdminAuthenticationFailureHandler());
		super.init(http);
	}

	@Override
	public void configure(H http) throws Exception {
		ApplicationContext applicationContext = http.getSharedObject(ApplicationContext.class);
		PasswordAdminAuthenticationSuccessHandler successHandler = http
			.getSharedObject(PasswordAdminAuthenticationSuccessHandler.class);
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
