package org.flooc.combo.dataoperation.validate;

import java.util.List;
import lombok.Getter;
import org.flooc.combo.common.exception.BizRuntimeException;
import org.flooc.combo.common.constant.BizErrorCode;

@Getter
public class ValidateRuntimeException extends BizRuntimeException {

  private final List<ValidateResult> results;

  public ValidateRuntimeException(BizErrorCode<?> errorCode, List<ValidateResult> validateResults) {
    super(errorCode);
    this.results = validateResults;
  }

}
