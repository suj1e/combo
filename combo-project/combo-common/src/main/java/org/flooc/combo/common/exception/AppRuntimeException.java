package org.flooc.combo.common.exception;

import lombok.Getter;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
public class AppRuntimeException extends RuntimeException {

  private final Integer code;

  public AppRuntimeException(Integer code) {
    super();
    this.code = code;
  }

  public AppRuntimeException(Integer code, String msg) {
    super(msg);
    this.code = code;
  }

  public AppRuntimeException(Integer code, Throwable cause) {
    super(cause);
    this.code = code;
  }

  public AppRuntimeException(Integer code, String msg, Throwable cause) {
    super(msg, cause);
    this.code = code;
  }

  public AppRuntimeException(ExceptionEnum<?> exceptionEnum) {
    super(exceptionEnum.getText());
    this.code = exceptionEnum.getCode();
  }


  public AppRuntimeException(ExceptionEnum<?> exceptionEnum, Throwable cause) {
    super(exceptionEnum.getText(), cause);
    this.code = exceptionEnum.getCode();
  }


}
