package org.flooc.combox.common;

import java.util.Optional;
import lombok.Getter;
import org.flooc.combo.common.constant.AppCode;
import org.flooc.combo.common.constant.CmpErrorCode;

/**
 * @author sujie
 * @since 1.0.0
 */
public final class CmpErrorCodes {

  private CmpErrorCodes() {
  }


  @Getter
  public enum StartupErrorCode implements CmpErrorCode<StartupErrorCode> {

    LoadComboVersionError(20001, "加载combo版本异常"),
    NotFindAppStartupIp(20002, "查找不到app启动机器ip");


    private final Integer code;
    private final String text;

    StartupErrorCode(Integer code, String text) {
      this.code = code;
      this.text = text;
    }

    public static Optional<StartupErrorCode> of(Integer code) {
      return Optional.ofNullable(AppCode.parse(StartupErrorCode.class, code));
    }

  }

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


  @Getter
  public enum SmsErrorCode implements
      CmpErrorCode<SmsErrorCode> {
    SmsSendError(40001,"短信发送失败"),
    ;

    private final Integer code;

    private final String text;

    SmsErrorCode(Integer code, String text) {
      this.code = code;
      this.text = text;
    }


    public static Optional<SecurityErrorCode> of(
        Integer code) {
      return Optional.ofNullable(AppCode.parse(SecurityErrorCode.class, code));
    }

  }

}
