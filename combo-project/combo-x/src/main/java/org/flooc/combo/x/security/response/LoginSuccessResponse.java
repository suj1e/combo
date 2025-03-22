package org.flooc.combo.x.security.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import org.flooc.combo.common.exception.CmpException;
import org.flooc.combo.x.constant.CmpExceptionConstant.SecurityExceptionEnum;
import org.flooc.combo.x.security.common.LoginSuccessToken;
import org.flooc.combo.x.security.config.JwtProperties;
import org.flooc.combo.x.security.jwt.JwtAuthenticationToken;
import org.flooc.combo.x.web.mvc.WebMvcResData;

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

  public static LoginSuccessResponse toResp(JwtProperties jwtProperties,
      JwtAuthenticationToken token) {
    try {
      LoginSuccessToken loginSuccessToken = LoginSuccessToken.toSuccessToken(jwtProperties, token);
      return LoginSuccessResponse.fromToken(loginSuccessToken);
    } catch (Exception e) {
      throw new CmpException(SecurityExceptionEnum.GenerateTokenError);
    }
  }

  public static void writeToResponse(HttpServletResponse response,
      LoginSuccessResponse successResponse)
      throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.writeValue(response.getOutputStream(), WebMvcResData.forOK(successResponse));
  }

}
