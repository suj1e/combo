package org.flooc.combo.common.exception;

import lombok.Getter;

/**
 * 业务异常
 *
 * @author sujie
 * @since 1.0.0
 */
@Getter
public class BizException extends AppRuntimeException {

  private final BizExceptionEnum<?> exceptionEnum;

  public BizException(BizExceptionEnum<?> exceptionEnum) {
    super(exceptionEnum);
    this.exceptionEnum = exceptionEnum;
  }


  public BizException(BizExceptionEnum<?> exceptionEnum, Throwable cause) {
    super(exceptionEnum, cause);
    this.exceptionEnum = exceptionEnum;
  }

}
