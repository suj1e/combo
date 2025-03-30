package org.flooc.combox.boot.security.admin.password;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.flooc.combo.security.exception.SecurityErrorCode;
import org.flooc.combo.security.exception.SecurityAuthenticationException;
import org.flooc.combo.webmvc.WebMvcResData;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * <p>
 * 1.认证流程中【已经进入认证流程了】出现异常，比如短信验证码输入不对这些等等发生了异常的处理
 * </p>
 *
 * @author sujie
 * @since 1.0.0
 */
public class PasswordAdminAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		ObjectMapper mapper = new ObjectMapper();
		if (exception instanceof SecurityAuthenticationException ex && SecurityErrorCode.PasswordIncorrect.equals(ex.getErrorCode())) {
			mapper.writeValue(response.getOutputStream(), WebMvcResData.fail(ex.getMessage()));
		}
		else {
			mapper.writeValue(response.getOutputStream(),
					WebMvcResData.fail(SecurityErrorCode.AuthenticationError));
		}
	}

}
