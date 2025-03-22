package org.flooc.combo.x.security.exception;

import lombok.Getter;
import org.flooc.combo.common.exception.CmpExceptionEnum;
import org.springframework.security.core.AuthenticationException;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
public class SecurityException extends AuthenticationException {

  private final CmpExceptionEnum<?> exceptionEnum;

  public SecurityException(CmpExceptionEnum<?> exceptionEnum) {
    super(exceptionEnum.getText());
    this.exceptionEnum = exceptionEnum;
  }

}
