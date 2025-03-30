package org.flooc.combox.boot.security.configurer.jwt;

import org.flooc.combox.boot.security.config.AuthProperties;
import org.flooc.combox.boot.security.config.JwtProperties;
import org.flooc.combox.boot.security.jwt.JwtFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author sujie
 * @since 1.0.0
 */
public final class JwtHttpConfigurer<H extends HttpSecurityBuilder<H>>
		extends AbstractHttpConfigurer<JwtHttpConfigurer<H>, H> {

	@Override
	public void configure(H http) throws Exception {
		ApplicationContext applicationContext = http.getSharedObject(ApplicationContext.class);
		JwtProperties jwtProperties = applicationContext.getBean(JwtProperties.class);
		AuthProperties authProperties = applicationContext.getBean(AuthProperties.class);
		JwtFilter jwtFilter = new JwtFilter();
		jwtFilter.setJwtProperties(jwtProperties);
		jwtFilter.setAuthProperties(authProperties);
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		super.configure(http);
	}

}
