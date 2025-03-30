package org.flooc.combo.common.constant;

/**
 * 业务异常码
 *
 * @author sujie
 * @since 1.0.0
 */
public interface BizErrorCode<T extends Enum<T> & BizErrorCode<T>> extends
    ErrorCode<T> {

}
