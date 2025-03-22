package org.flooc.combo.x.security.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.flooc.combo.common.exception.CmpException;
import org.flooc.combo.x.constant.CmpExceptionConstant.SecurityExceptionEnum;
import org.flooc.combo.x.security.config.JwtProperties;
import org.flooc.combo.x.security.constant.JwtConstant;
import org.flooc.combo.x.security.jwt.JwtAuthenticationToken;
import org.flooc.combo.x.security.jwt.JwtUtils;
import org.flooc.combo.x.security.jwt.JwtUtils.JwtToken;

/**
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
@Getter
@Setter
public class LoginSuccessToken {

  private String accessToken;

  private String tokenType;

  private Date issuedAt;

  private Date expiresAt;

  private long expiresIn;

  private String refreshToken;

  private Date refreshTokenExpiresAt;

  private long refreshTokenExpiresIn;

  private Map<String, Object> additionalParameters;

  public static LoginSuccessToken toSuccessToken(JwtProperties jwtProperties,
      JwtAuthenticationToken token) {
    try {
      Map<String, Object> claimMap = new HashMap<>();
      ObjectMapper mapper = new ObjectMapper();
      claimMap.put(JwtConstant.JWT_AUTHENTICATION_TOKEN, mapper.writeValueAsString(token));
      JwtToken accessToken = JwtUtils.generate(jwtProperties.getKeyId(),
          jwtProperties.getExpiresIn(),
          token.getPrincipal().toString(), claimMap);
      JwtToken refreshToken = JwtUtils.generate(jwtProperties.getKeyId(),
          jwtProperties.getRefreshTokenExpiresIn(), token.getPrincipal().toString(), claimMap);
      LoginSuccessToken loginSuccessToken = buildToken(jwtProperties, accessToken, refreshToken);
      loginSuccessToken.setAdditionalParameters(Collections.emptyMap());
      return loginSuccessToken;
    } catch (Exception e) {
      log.error("Failed to generate loginSuccessToken", e);
      throw new CmpException(SecurityExceptionEnum.GenerateTokenError);
    }
  }

  private static LoginSuccessToken buildToken(JwtProperties jwtProperties, JwtToken accessToken,
      JwtToken refreshToken) {
    LoginSuccessToken loginSuccessToken = new LoginSuccessToken();
    loginSuccessToken.setAccessToken(accessToken.getToken());
    loginSuccessToken.setTokenType(jwtProperties.getTokenType());
    loginSuccessToken.setIssuedAt(accessToken.getIat());
    loginSuccessToken.setExpiresIn(accessToken.getEi());
    loginSuccessToken.setExpiresAt(accessToken.getEt());
    loginSuccessToken.setRefreshToken(refreshToken.getToken());
    loginSuccessToken.setRefreshTokenExpiresAt(refreshToken.getEt());
    loginSuccessToken.setRefreshTokenExpiresIn(refreshToken.getEi());
    return loginSuccessToken;
  }

}
