package org.flooc.combo.common.util;

import org.flooc.combo.common.exception.BizException;
import org.flooc.combo.common.exception.BizExceptionEnum;

/**
 * @author sujie
 * @since 1.0.0
 */
public final class BizAssert {

  private BizAssert(){}

  public void state(boolean expression, BizExceptionEnum<?> exceptionEnum) {
    if (!expression) {
      throw new BizException(exceptionEnum);
    }
  }

}
