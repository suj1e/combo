package org.flooc.combo.dataoperation.core;

import java.util.function.Supplier;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface Creator<T> {

	Handler<T> create(Supplier<T> t);

}
