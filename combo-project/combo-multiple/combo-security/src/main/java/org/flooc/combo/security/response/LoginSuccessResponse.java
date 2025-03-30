package org.flooc.combo.security.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import org.flooc.combo.common.exception.CmpRuntimeException;
import org.flooc.combo.security.common.LoginSuccessToken;
import org.flooc.combo.security.exception.SecurityErrorCode;
import org.flooc.combo.security.jwt.JwtAuthenticationToken;
import org.flooc.combo.webmvc.WebMvcResData;

/**
 * <p>
 * 用于转换loginSuccessToken写入响应
 * </p>
 *
 * @author sujie
 * @since 1.0.0
 */
@Getter
@Builder
public class LoginSuccessResponse {


  private String accessToken;


  private String tokenType;


  private Instant issuedAt;


  private Instant expiresAt;


  private long expiresIn;


  private String refreshToken;


  private Instant refreshTokenExpiresAt;


  private long refreshTokenExpiresIn;


  private Map<String, Object> additionalParameters;

  public static LoginSuccessResponse fromToken(LoginSuccessToken token) {
    return LoginSuccessResponse.builder()
        .accessToken(token.getAccessToken())
        .tokenType(token.getTokenType())
        .issuedAt(token.getIssuedAt().toInstant())
        .expiresAt(token.getExpiresAt().toInstant())
        .expiresIn(token.getExpiresIn())
        .refreshToken(token.getRefreshToken())
        .refreshTokenExpiresAt(token.getRefreshTokenExpiresAt().toInstant())
        .refreshTokenExpiresIn(token.getRefreshTokenExpiresIn())
        .additionalParameters(token.getAdditionalParameters())
        .build();
  }

  public static LoginSuccessResponse toResp(String keyId, long expiresIn,
      long refreshTokenExpiresIn, String tokenType,
      JwtAuthenticationToken token) {
    try {
      LoginSuccessToken loginSuccessToken = LoginSuccessToken.toSuccessToken(keyId, expiresIn,
          refreshTokenExpiresIn, tokenType, token);
      return LoginSuccessResponse.fromToken(loginSuccessToken);
    } catch (Exception e) {
      throw new CmpRuntimeException(SecurityErrorCode.GenerateTokenError);
    }
  }

  public static void writeToResponse(HttpServletResponse response,
      LoginSuccessResponse successResponse)
      throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.writeValue(response.getOutputStream(), WebMvcResData.success(successResponse));
  }

}
