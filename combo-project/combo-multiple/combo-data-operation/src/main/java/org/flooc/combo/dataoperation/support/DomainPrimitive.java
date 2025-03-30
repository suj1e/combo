package org.flooc.combo.dataoperation.support;

import java.io.Serializable;

/**
 * 最基础的值对象，提供值对象的基本行为，比如Email、Phone等
 *
 * @author sujie
 * @since 1.0.0
 */
public interface DomainPrimitive<V> extends Serializable {

	V value();

	void doValidate(V value);

	default void apply(V value) {
	}

}
