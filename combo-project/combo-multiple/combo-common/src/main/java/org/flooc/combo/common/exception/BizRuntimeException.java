package org.flooc.combo.common.exception;

import lombok.Getter;
import org.flooc.combo.common.constant.BizErrorCode;

/**
 * 业务-运行时异常
 *
 * @author sujie
 * @since 1.0.0
 */
@Getter
public class BizRuntimeException extends RuntimeException implements BizError {

  private final BizErrorCode<?> errorCode;


  public BizRuntimeException(BizErrorCode<?> errorCode) {
    super(errorCode.getText());
    this.errorCode = errorCode;
  }


  public BizRuntimeException(BizErrorCode<?> errorCode, Throwable cause) {
    super(errorCode.getText(), cause);
    this.errorCode = errorCode;
  }

}
