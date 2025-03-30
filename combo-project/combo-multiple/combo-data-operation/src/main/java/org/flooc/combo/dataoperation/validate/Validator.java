package org.flooc.combo.dataoperation.validate;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface Validator {

	<T> void validate(T data);

	boolean supports(Class<?> clazz);

}
