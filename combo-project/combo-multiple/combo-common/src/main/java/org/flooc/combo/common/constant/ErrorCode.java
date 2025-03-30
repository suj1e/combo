package org.flooc.combo.common.constant;

/**
 * 异常码
 *
 * @author sujie
 * @since 1.0.0
 */
public interface ErrorCode<T extends Enum<T> & ErrorCode<T>> extends AppCode<T> {

}
