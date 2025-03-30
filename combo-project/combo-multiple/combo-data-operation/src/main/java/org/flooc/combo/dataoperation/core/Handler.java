package org.flooc.combo.dataoperation.core;

import java.util.function.Consumer;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface Handler<T> {

	Executor<T> handle(Consumer<T> consumer);

}
