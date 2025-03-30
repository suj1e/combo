package org.flooc.combox.boot.security.admin.password;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.flooc.combo.security.jwt.JwtAuthenticationToken;
import org.flooc.combo.security.jwt.JwtWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * <p>
 * 1.根据provider认证处理成功后的处理器，接收它认证后的AuthenticationToken 2.eg.写jwt在response中
 * </p>
 *
 * @author sujie
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class PasswordAdminAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  @Autowired
  private final JwtWriter jwtWriter;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException {
    PasswordAdminAuthenticationToken authenticationToken = (PasswordAdminAuthenticationToken) authentication;
    // 生成token
    JwtAuthenticationToken jwtToken = PasswordAdminAuthenticationToken.toJwtToken(
        authenticationToken);
    jwtWriter.writeToResponse(response, jwtToken);
  }

}
