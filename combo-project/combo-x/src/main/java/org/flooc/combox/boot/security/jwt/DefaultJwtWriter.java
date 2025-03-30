package org.flooc.combox.boot.security.jwt;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.flooc.combo.security.jwt.JwtAuthenticationToken;
import org.flooc.combo.security.jwt.JwtWriter;
import org.flooc.combo.security.response.LoginSuccessResponse;
import org.flooc.combox.boot.security.config.JwtProperties;

/**
 * @author sujie
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class DefaultJwtWriter implements JwtWriter {

  private final JwtProperties jwtProperties;

  @Override
  public void writeToResponse(HttpServletResponse response,
      JwtAuthenticationToken jwtAuthenticationToken) throws IOException {
    LoginSuccessResponse.writeToResponse(response,
        LoginSuccessResponse.toResp(jwtProperties.getKeyId(), jwtProperties.getExpiresIn(),
            jwtProperties.getRefreshTokenExpiresIn(), jwtProperties.getTokenType(),
            jwtAuthenticationToken));
  }
}
