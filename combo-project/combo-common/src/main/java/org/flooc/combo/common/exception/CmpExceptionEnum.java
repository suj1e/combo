package org.flooc.combo.common.exception;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface CmpExceptionEnum<T extends Enum<T> & CmpExceptionEnum<T>> extends
    ExceptionEnum<T> {

}
