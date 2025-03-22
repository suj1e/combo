package org.flooc.combo.x.security.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.flooc.combo.x.security.config.AuthProperties.FilterChainIgnoreUrl;
import org.flooc.combo.x.security.configurer.JwtHttpConfigurer;
import org.flooc.combo.x.security.configurer.admin.PasswordAdminAuthenticationConfigurer;
import org.flooc.combo.x.security.configurer.admin.SmsAdminAuthenticationConfigurer;
import org.flooc.combo.x.security.constant.AuthUrlConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity.IgnoredRequestConfigurer;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfigurationSource;

/**
 * Tips: 只要有一个security filter chain匹配到了，那么就会只进那一个security filter chain <br/>
 * 所以定义security filter chain bean的顺序很重要，security filter chain的bean定义的order很重要，决定了匹配的顺序
 *
 * @author sujie
 * @since 1.0.0
 */
@EnableConfigurationProperties(value = { AuthProperties.class, JwtProperties.class })
@Configuration(proxyBeanMethods = false)
public class SecurityFilterChainConfiguration {

	private static final String[] DEFAULT_IGNORES = { "/actuator/**" };

	@Autowired
	private AuthProperties authProperties;

	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private CorsConfigurationSource configurationSource;

	/**
	 * @return 全局过滤定制器bean
	 */
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> {
			IgnoredRequestConfigurer ignoring = web.ignoring();
			for (String defaultIgnore : DEFAULT_IGNORES) {
				ignoring.requestMatchers(new AntPathRequestMatcher(defaultIgnore));
			}
			String[] webIgnoreUrls = authProperties.getWebIgnoreUrls();
			if (webIgnoreUrls != null) {
				for (String ignoreUrl : webIgnoreUrls) {
					ignoring.requestMatchers(new AntPathRequestMatcher(ignoreUrl));
				}
			}
		};
	}

	@Bean
	@Order(1)
	@ConditionalOnProperty(prefix = "app.auth", name = "authentication", havingValue = "true")
	public SecurityFilterChain passwordAdminLoginFilterChain(HttpSecurity http) throws Exception {
		// step builder pattern
		http.securityMatcher(AuthUrlConstant.PASSWORD_ADMIN_LOGIN_DISPATCH_URL)
			.csrf(AbstractHttpConfigurer::disable)
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.formLogin(AbstractHttpConfigurer::disable)
			.exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint))
			.cors(cors -> cors.configurationSource(configurationSource))
			.authorizeHttpRequests(request -> request.requestMatchers(AuthUrlConstant.PASSWORD_ADMIN_LOGIN_DISPATCH_URL)
				.permitAll()
				.anyRequest()
				.authenticated());
		http.with(new PasswordAdminAuthenticationConfigurer<>(), configurer -> {
		});
		return http.build();
	}

	@Bean
	@Order(2)
	@ConditionalOnProperty(prefix = "app.auth", name = "authentication", havingValue = "true")
	public SecurityFilterChain smsAdminLoginFilterChain(HttpSecurity http) throws Exception {
		// step builder pattern
		// cors必须配置，它默认开启，时机是在spring mvc前面的，spring security在请求处理的前期阶段进行安全控制
		http.securityMatcher(AuthUrlConstant.SMS_ADMIN_LOGIN_DISPATCH_URL)
			.csrf(AbstractHttpConfigurer::disable)
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.formLogin(AbstractHttpConfigurer::disable)
			.exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint))
			.cors(cors -> cors.configurationSource(configurationSource))
			.authorizeHttpRequests(request -> request.requestMatchers(AuthUrlConstant.SMS_ADMIN_LOGIN_DISPATCH_URL)
				.permitAll()
				.anyRequest()
				.authenticated());
		http.with(new SmsAdminAuthenticationConfigurer<>(), configurer -> {
		});
		return http.build();
	}

	@Bean
	@Order(3)
	@ConditionalOnProperty(prefix = "app.auth", name = "authorization", havingValue = "true", matchIfMissing = true)
	public SecurityFilterChain jwtFilterChain(HttpSecurity http) throws Exception {
		http.securityMatcher(authProperties.getResourceAuthPattern(), AuthUrlConstant.REFRESH_TOKEN_DISPATCH_URL)
			.csrf(AbstractHttpConfigurer::disable)
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.formLogin(AbstractHttpConfigurer::disable)
			.exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint))
			.cors(cors -> cors.configurationSource(configurationSource))
			.authorizeHttpRequests(
					request -> request.requestMatchers(combChainIgnoreUrls()).permitAll().anyRequest().authenticated());
		http.with(new JwtHttpConfigurer<>(), configurer -> {
		});
		return http.build();
	}

	private String[] combChainIgnoreUrls() {
		List<String> ignoreUrls = new ArrayList<>();
		ignoreUrls.add(AuthUrlConstant.REFRESH_TOKEN_DISPATCH_URL);
		FilterChainIgnoreUrl chainIgnore = authProperties.getJwtChainIgnore();
		if (chainIgnore != null && chainIgnore.getIgnoreUrls() != null) {
			ignoreUrls.addAll(Arrays.asList(chainIgnore.getIgnoreUrls()));
		}
		return ignoreUrls.toArray(String[]::new);
	}

}
