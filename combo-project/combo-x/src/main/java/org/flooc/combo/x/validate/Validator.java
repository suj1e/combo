package org.flooc.combo.x.validate;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface Validator {

	<T> void validate(T data);

	boolean support(Class<?> clazz);

}
