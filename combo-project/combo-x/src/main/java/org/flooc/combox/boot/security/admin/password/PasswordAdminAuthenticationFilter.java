package org.flooc.combox.boot.security.admin.password;

import static org.flooc.combo.security.constant.LoginUrls.PASSWORD_ADMIN;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * <p>
 * 1. 属于AuthenticationFilter 2. 处理http请求及转换 3. 转换后委托给AuthenticationManager认证
 * </p>
 *
 * @author sujie
 * @since 1.0.0
 */
public class PasswordAdminAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private static final AntPathRequestMatcher DEFAULT_AUTHENTICATION_PATH = new AntPathRequestMatcher(
			PASSWORD_ADMIN);

	public PasswordAdminAuthenticationFilter(AuthenticationManager authenticationManager,
			AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler) {
		super(DEFAULT_AUTHENTICATION_PATH);
		setAuthenticationManager(authenticationManager);
		setAuthenticationSuccessHandler(successHandler);
		setAuthenticationFailureHandler(failureHandler);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		if (!HttpMethod.POST.matches(request.getMethod())) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		ObjectMapper mapper = new ObjectMapper();
		PasswordAdminLoginRequest smsAdminLoginRequest = mapper.readValue(request.getInputStream(),
				PasswordAdminLoginRequest.class);
		PasswordAdminAuthenticationToken authRequest = PasswordAdminAuthenticationToken
			.unauthenticated(smsAdminLoginRequest);
		authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
		return this.getAuthenticationManager().authenticate(authRequest);
	}

}
