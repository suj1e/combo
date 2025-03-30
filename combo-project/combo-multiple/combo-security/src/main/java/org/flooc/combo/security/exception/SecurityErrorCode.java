package org.flooc.combo.security.exception;

import java.util.Optional;
import lombok.Getter;
import org.flooc.combo.common.constant.AppCode;
import org.flooc.combo.common.constant.CmpErrorCode;

@Getter
public enum SecurityErrorCode implements
    CmpErrorCode<SecurityErrorCode> {

  Unauthorized(30002, "未授权"),
  GenerateTokenError(30003, "生成token失败"),
  AuthEntryError(30004, "认证异常"),
  VerifyCodeExpired(30005, "验证码过期"),
  PasswordIncorrect(30006, "密码错误"),
  TokenExpired(30007, "令牌已过期"),
  TokenParseError(30008, "令牌解析错误"),
  NoRefreshToken(30009, "无刷新令牌"),
  InvalidRefreshToken(30010, "无效刷新令牌"),
  NoSuchUser(30011, "无此用户"),
  VerifyCodeIncorrect(30012, "验证码错误"),
  AuthenticationError(30013, "认证异常"),
  JwtError(30014, "JWT错误"),
  AUTH_ENTRY_ERROR(30015, "认证异常"),
  ;

  private final Integer code;

  private final String text;

  SecurityErrorCode(Integer code, String text) {
    this.code = code;
    this.text = text;
  }


  public static Optional<SecurityErrorCode> of(
      Integer code) {
    return Optional.ofNullable(AppCode.parse(SecurityErrorCode.class, code));
  }

}