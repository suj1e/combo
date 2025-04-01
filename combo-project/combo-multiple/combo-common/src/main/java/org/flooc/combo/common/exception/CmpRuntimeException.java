package org.flooc.combo.common.exception;

import lombok.Getter;
import org.flooc.combo.common.constant.CmpErrorCode;

/**
 * 组件-运行时异常
 *
 * @author sujie
 * @since 1.0.0
 */
@Getter
public class CmpRuntimeException extends RuntimeException implements CmpError {

  private final CmpErrorCode<?> errorCode;

  public CmpRuntimeException(CmpErrorCode<?> errorCode) {
    super(errorCode.getText());
    this.errorCode = errorCode;
  }

  public CmpRuntimeException(CmpErrorCode<?> errorCode, String errorMessage) {
    super(errorMessage);
    this.errorCode = errorCode;
  }

  public CmpRuntimeException(CmpErrorCode<?> errorCode, Throwable cause) {
    super(errorCode.getText(), cause);
    this.errorCode = errorCode;
  }

  public CmpRuntimeException(CmpErrorCode<?> errorCode, String errorMessage, Throwable cause) {
    super(errorMessage, cause);
    this.errorCode = errorCode;
  }


}
