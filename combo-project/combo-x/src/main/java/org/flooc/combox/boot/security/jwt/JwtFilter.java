package org.flooc.combox.boot.security.jwt;

import static org.flooc.combo.security.constant.JwtKey.JWT_AUTHENTICATION_TOKEN;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.flooc.combo.security.common.JwtTokenVerifyResult;
import org.flooc.combo.security.constant.LoginUrls;
import org.flooc.combo.security.exception.SecurityErrorCode;
import org.flooc.combo.security.jwt.JwtAuthenticationToken;
import org.flooc.combo.security.jwt.JwtAuthenticationTokenMapper;
import org.flooc.combo.security.jwt.JwtUtils;
import org.flooc.combo.security.response.LoginSuccessResponse;
import org.flooc.combo.webmvc.WebMvcResData;
import org.flooc.combox.boot.security.config.AuthProperties;
import org.flooc.combox.boot.security.config.AuthProperties.FilterChainIgnoreUrl;
import org.flooc.combox.boot.security.config.JwtProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author sujie
 * @since 1.0.0
 */
@Setter
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

  private static final String X_REFRESH_TOKEN = "X-Refresh-Token";

  private JwtProperties jwtProperties;

  private AuthProperties authProperties;

  @Override
  protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain) throws ServletException, IOException {
    String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (StringUtils.hasText(header) && !determineIgnoreIfExistAuthHeader(request)) {
      String token = getToken(header);
      JwtTokenVerifyResult result = JwtUtils.verifyToken(jwtProperties.getKeyId(), token);
      if (result.isValid() && !isRefreshToken(request)) {
        String authenticationClaim = JwtUtils.toAuthenticationClaim(JWT_AUTHENTICATION_TOKEN,
            token);
        JwtAuthenticationToken authentication = JwtAuthenticationTokenMapper.toToken(
            authenticationClaim);
        if (authentication == null) {
          writeErrorToResponse(response, SecurityErrorCode.TokenParseError);
          return;
        }
        // TODO 多余的权限或其他信息可以通过缓存加载
        // authentication.getAuthorities().addAll();
        // 赋予当前请求上下文的认证对象
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 认证成功后，后续的filter处理请求会委托给servlet#doServlet
        filterChain.doFilter(request, response);
      } else {
        if (result.isValid()) {
          // 走到这里必然是refreshToken请求，且当前accessToken有效，目前允许刷新令牌
          refreshTokenToResponse(response, token);
        } else {
          SecurityErrorCode resType = result.getSecurityErrorCode();
          if (SecurityErrorCode.TokenExpired == resType) {
            if (isRefreshToken(request)) {
              String refreshToken = request.getHeader(X_REFRESH_TOKEN);
              if (!StringUtils.hasText(refreshToken)) {
                writeErrorToResponse(response, SecurityErrorCode.NoRefreshToken);
              } else {
                // 校验refreshToken有效性
                JwtTokenVerifyResult refreshTokenVerifyRes = JwtUtils
                    .verifyToken(jwtProperties.getKeyId(), refreshToken);
                if (refreshTokenVerifyRes.isValid()) {
                  refreshTokenToResponse(response, token);
                } else {
                  writeErrorToResponse(response, SecurityErrorCode.InvalidRefreshToken);
                }
              }
            } else {
              // token过期则抛token过期异常，让前端请求刷新token
              writeErrorToResponse(response, SecurityErrorCode.TokenExpired);
            }
          } else {
            writeErrorToResponse(response, resType);
          }
        }

      }
    } else {
      filterChain.doFilter(request, response);
    }
  }

  private void refreshTokenToResponse(HttpServletResponse response, String token)
      throws IOException {
    String authenticationClaim = JwtUtils.toAuthenticationClaim(JWT_AUTHENTICATION_TOKEN, token);
    JwtAuthenticationToken authentication = JwtAuthenticationTokenMapper.toToken(
        authenticationClaim);
    if (authentication == null) {
      writeErrorToResponse(response, SecurityErrorCode.TokenParseError);
    } else {
      refreshToken(response, authentication);
    }
  }

  private static boolean isRefreshToken(HttpServletRequest request) {
    return HttpMethod.POST.matches(request.getMethod())
        && request.getRequestURI().endsWith(LoginUrls.REFRESH_TOKEN);
  }

  private void refreshToken(HttpServletResponse response, JwtAuthenticationToken authentication)
      throws IOException {
    if (log.isDebugEnabled()) {
      log.debug("Refreshing the token now");
    }
    LoginSuccessResponse.writeToResponse(response,
        LoginSuccessResponse.toResp(jwtProperties.getKeyId(), jwtProperties.getExpiresIn(),
            jwtProperties.getRefreshTokenExpiresIn(), jwtProperties.getTokenType(),
            authentication));
  }

  private void writeErrorToResponse(HttpServletResponse response,
      SecurityErrorCode errorCode)
      throws IOException {
    ObjectMapper om = new ObjectMapper();
    om.writeValue(response.getOutputStream(),
        WebMvcResData.fail(SecurityErrorCode.JwtError));
  }

  private static String getToken(String header) {
    return header.replaceFirst("Bearer ", "");
  }

  /**
   * 如果存在认证请求头才需要用到此方法去断言是否是过滤不需要处理的，否则默认情况就可以走其他过滤器
   *
   * @param request 当前请求
   * @return ignore handle
   */
  private boolean determineIgnoreIfExistAuthHeader(HttpServletRequest request) {
    FilterChainIgnoreUrl jwtChainIgnore = authProperties.getJwtChainIgnore();
    if (jwtChainIgnore != null) {
      String[] ignoreUrls = jwtChainIgnore.getIgnoreUrls();
      if (ignoreUrls != null) {
        for (String ignoreUrl : ignoreUrls) {
          if (AntPathRequestMatcher.antMatcher(ignoreUrl).matches(request)) {
            return true;
          }
        }
      }
    }
    return false;
  }

}
