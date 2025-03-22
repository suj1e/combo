package org.flooc.combo.common.exception;

import org.flooc.combo.common.constant.AppEnum;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface ExceptionEnum<T extends Enum<T> & ExceptionEnum<T>> extends AppEnum<T> {

}
