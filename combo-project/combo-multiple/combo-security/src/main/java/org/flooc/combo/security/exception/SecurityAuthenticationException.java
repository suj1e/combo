package org.flooc.combo.security.exception;

import lombok.Getter;
import org.flooc.combo.common.exception.CmpError;
import org.springframework.security.core.AuthenticationException;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
public class SecurityAuthenticationException extends AuthenticationException implements CmpError {

  private final SecurityErrorCode errorCode;

  public SecurityAuthenticationException(SecurityErrorCode errorCode) {
    super(errorCode.getText());
    this.errorCode = errorCode;
  }

}
