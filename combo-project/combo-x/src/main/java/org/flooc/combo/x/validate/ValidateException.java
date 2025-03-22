package org.flooc.combo.x.validate;

import java.util.List;
import lombok.Getter;
import org.flooc.combo.common.exception.CmpException;
import org.flooc.combo.x.constant.CmpExceptionConstant.ValidateExceptionEnum;

@Getter
public class ValidateException extends CmpException {

  private final List<ValidateResult> result;

  public ValidateException(ValidateExceptionEnum exceptionEnum, List<ValidateResult> validateResults) {
    super(exceptionEnum);
    this.result = validateResults;
  }

}
