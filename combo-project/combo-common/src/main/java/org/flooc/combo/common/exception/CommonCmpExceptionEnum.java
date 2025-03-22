package org.flooc.combo.common.exception;

import java.util.Optional;
import lombok.Getter;
import org.flooc.combo.common.constant.AppEnum;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
public enum CommonCmpExceptionEnum implements CmpExceptionEnum<CommonCmpExceptionEnum> {
  CmpError(10000,"组件异常"),
  ;


  private final Integer code;
  private final String text;

  CommonCmpExceptionEnum(Integer code, String text) {
    this.code = code;
    this.text = text;
  }

  public static Optional<CommonCmpExceptionEnum> of(Integer code) {
    return Optional.ofNullable(AppEnum.parse(CommonCmpExceptionEnum.class, code));
  }

}
