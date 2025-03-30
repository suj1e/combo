package org.flooc.combo.dataoperation.core;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

/**
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
public class ModelCreator<M> implements Operation, Creator<M>, Handler<M>, Executor<M> {

	private ModelProcessor<M> modelProcessor;

	private M t;


	private Consumer<M> successHook = t -> {
		if (log.isDebugEnabled()) {
			log.debug("Successfully create model of {}", t.getClass().getSimpleName());
		}
	};

	private Consumer<? super Throwable> errorHook = e -> {
		log.error("Failed to create model of {}, errorMsg: {}", t.getClass().getSimpleName(), e.getMessage());
	};

	public ModelCreator() {

	}

	public ModelCreator(ModelProcessor<M> modelProcessor) {
		this.modelProcessor = modelProcessor;
	}

	@Override
	public Handler<M> create(Supplier<M> t) {
		this.t = t.get();
		return this;
	}

	@Override
	public Optional<M> execute() {
		M processed = t;
		try {
			if (modelProcessor != null) {
				modelProcessor.process(t);
			}
			successHook.accept(processed);
		}
		catch (Exception e) {
			errorHook.accept(e);
		}
		return Optional.ofNullable(processed);
	}

	@Override
	public Executor<M> successHook(Consumer<M> consumer) {
		this.successHook = consumer;
		return this;
	}

	@Override
	public Executor<M> errorHook(Consumer<? super Throwable> consumer) {
		this.errorHook = consumer;
		return this;
	}

	@Override
	public Executor<M> handle(Consumer<M> consumer) {
		Assert.notNull(t, "Model must not be null");
		consumer.accept(t);
		return this;
	}

}
