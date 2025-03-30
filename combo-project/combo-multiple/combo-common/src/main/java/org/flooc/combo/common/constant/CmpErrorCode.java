package org.flooc.combo.common.constant;

/**
 * 组件异常码
 *
 * @author sujie
 * @since 1.0.0
 */
public interface CmpErrorCode<T extends Enum<T> & CmpErrorCode<T>> extends
    ErrorCode<T> {

}
