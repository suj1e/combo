package org.flooc.combo.dataoperation.core;

import java.util.function.Supplier;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface Applier<T> {

	Executor<T> apply(Supplier<T> t);

}
