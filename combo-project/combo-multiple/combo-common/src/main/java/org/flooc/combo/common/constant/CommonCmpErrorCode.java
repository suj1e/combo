package org.flooc.combo.common.constant;

import java.util.Optional;
import lombok.Getter;

/**
 * 公共-组件异常码
 *
 * @author sujie
 * @since 1.0.0
 */
@Getter
public enum CommonCmpErrorCode implements CmpErrorCode<CommonCmpErrorCode> {

  CmpError(10000, "组件异常"),
  NotNull(10001, "值不能为null"),
  NotEmpty(10002, "值不能为空"),
  CommonSpiError(10003, "spi公共模块组件异常"),
  ;


  private final Integer code;
  private final String text;

  CommonCmpErrorCode(Integer code, String text) {
    this.code = code;
    this.text = text;
  }

  public static Optional<CommonCmpErrorCode> of(Integer code) {
    return Optional.ofNullable(AppCode.parse(CommonCmpErrorCode.class, code));
  }

}
