package org.flooc.combo.common.exception;

import lombok.Getter;

/**
 * 组件异常
 *
 * @author sujie
 * @since 1.0.0
 */
@Getter
public class CmpException extends AppRuntimeException {

  private final CmpExceptionEnum<?> exceptionEnum;

  public CmpException(CmpExceptionEnum<?> exceptionEnum) {
    super(exceptionEnum);
    this.exceptionEnum = exceptionEnum;
  }

  public CmpException(CmpExceptionEnum<?> exceptionEnum, Throwable cause) {
    super(exceptionEnum, cause);
    this.exceptionEnum = exceptionEnum;
  }

}
