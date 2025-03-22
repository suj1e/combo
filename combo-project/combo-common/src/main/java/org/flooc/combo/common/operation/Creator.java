package org.flooc.combo.common.operation;

import java.util.function.Supplier;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface Creator<T> {

	Handler<T> create(Supplier<T> t);

}
