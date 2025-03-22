package org.flooc.combo.common.exception;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface BizExceptionEnum<T extends Enum<T> & BizExceptionEnum<T>> extends
    ExceptionEnum<T> {

}
