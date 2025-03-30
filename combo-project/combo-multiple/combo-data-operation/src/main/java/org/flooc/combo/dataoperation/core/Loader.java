package org.flooc.combo.dataoperation.core;

import java.util.function.Supplier;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface Loader<T> {

	Handler<T> load(Supplier<T> t);

}
