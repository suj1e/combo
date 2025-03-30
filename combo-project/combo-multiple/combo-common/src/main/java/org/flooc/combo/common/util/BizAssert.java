package org.flooc.combo.common.util;

import org.flooc.combo.common.exception.BizRuntimeException;
import org.flooc.combo.common.constant.BizErrorCode;

/**
 * 业务断言工具
 *
 * @author sujie
 * @since 1.0.0
 */
public final class BizAssert {

  private BizAssert(){}

  public static void state(boolean expression, BizErrorCode<?> errorCode) {
    if (!expression) {
      throw new BizRuntimeException(errorCode);
    }
  }

}
