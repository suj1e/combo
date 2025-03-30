package org.flooc.combo.dataoperation.core;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.groups.Default;
import java.util.List;
import java.util.Set;
import org.flooc.combo.common.constant.CommonBizErrorCode;
import org.flooc.combo.dataoperation.validate.ValidateRuntimeException;
import org.flooc.combo.dataoperation.validate.ValidateGroup;
import org.flooc.combo.dataoperation.validate.ValidateResult;
import org.springframework.util.CollectionUtils;

/**
 * @author sujie
 * @since 1.0.0
 */
public abstract class AbstractEntityOperation implements Operation {

  private static final Validator VALIDATOR;

  static {
    try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
      VALIDATOR = validatorFactory.getValidator();
    }
  }

  public <T> void doValidate(T t, Class<? extends ValidateGroup> group) {
    Set<ConstraintViolation<T>> constraints = VALIDATOR.validate(t, group, Default.class);
    if (!CollectionUtils.isEmpty(constraints)) {
      List<ValidateResult> results = constraints.stream()
          .map(cv -> new ValidateResult(cv.getPropertyPath().toString(), cv.getMessage()))
          .toList();
      throw new ValidateRuntimeException(CommonBizErrorCode.ValidateError, results);
    }
  }

}
