package org.flooc.combo.dataoperation.core;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface Executor<T> {

	Optional<T> execute();

	Executor<T> successHook(Consumer<T> consumer);

	Executor<T> errorHook(Consumer<? super Throwable> consumer);

}
