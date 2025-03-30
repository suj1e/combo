package org.flooc.combo.security.enums;

import java.util.Optional;
import lombok.Getter;
import org.flooc.combo.common.constant.AppCode;
import org.flooc.combo.common.constant.BizCode;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
public enum AuthenticationWay implements BizCode<AuthenticationWay> {
  SMS(1, "短信"), PASSWORD(2, "密码"), EMAIL(3, "邮件"), THIRD_PARTY(4, "三方");

  private final Integer code;

  private final String text;

  AuthenticationWay(Integer code, String text) {
    this.code = code;
    this.text = text;
  }

  public static Optional<AuthenticationWay> of(Integer code) {
    return Optional.ofNullable(AppCode.parse(AuthenticationWay.class, code));
  }

}
