package org.flooc.combo.common.exception;

import org.flooc.combo.common.constant.CmpErrorCode;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface CmpError {

  CmpErrorCode<?> getErrorCode();

}
