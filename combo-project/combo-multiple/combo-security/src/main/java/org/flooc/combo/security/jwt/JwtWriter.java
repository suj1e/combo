package org.flooc.combo.security.jwt;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface JwtWriter {


  void writeToResponse(HttpServletResponse response, JwtAuthenticationToken jwtAuthenticationToken)
      throws IOException;

}
