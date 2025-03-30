package org.flooc.combo.common.exception;

import org.flooc.combo.common.constant.BizErrorCode;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface BizError {

  BizErrorCode<?> getErrorCode();

}
