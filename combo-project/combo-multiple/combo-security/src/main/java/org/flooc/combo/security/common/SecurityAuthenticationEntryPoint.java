package org.flooc.combo.security.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.flooc.combo.security.exception.SecurityErrorCode;
import org.flooc.combo.webmvc.WebMvcResData;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * <p>
 * 1.没有匹配到相应的过滤器链 <br/>
 * 2.如果security filter chain匹配到了，匹配的是OncePerRequestFilter,
 * 但是在doFilter流程中出现异常，则也会被该组件处理<br/>
 * 3.如果security filter chain匹配到了，匹配的是AuthenticationFilter,
 * 但是不符合认证条件，也就是未进入认证流程就出现异常，则也会被该组件处理
 * </p>
 *
 * @author sujie
 * @since 1.0.0
 */
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), WebMvcResData.fail(SecurityErrorCode.AuthEntryError));
	}

}
