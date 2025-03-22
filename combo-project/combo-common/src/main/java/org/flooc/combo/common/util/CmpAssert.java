package org.flooc.combo.common.util;

import org.flooc.combo.common.exception.CmpException;
import org.flooc.combo.common.exception.CmpExceptionEnum;

/**
 * @author sujie
 * @since 1.0.0
 */
public final class CmpAssert {

  private CmpAssert(){}

  public void state(boolean expression, CmpExceptionEnum<?> exceptionEnum) {
    if (!expression) {
      throw new CmpException(exceptionEnum);
    }
  }

}
