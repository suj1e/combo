package org.flooc.combo.x.data.common.operation;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.flooc.combo.common.operation.Applyer;
import org.flooc.combo.common.operation.Executor;
import org.flooc.combo.common.operation.Handler;
import org.flooc.combo.common.operation.Loader;
import org.flooc.combo.x.data.common.repo.CmdRepo;
import org.flooc.combo.x.validate.UpdateGroup;
import org.flooc.combo.x.validate.ValidateExecutor;
import org.flooc.combo.x.web.startup.SpringAppUtil;
import org.springframework.util.Assert;

/**
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
public class EUpdater<E, I> extends AbstractEOperation implements Applyer<E>, Loader<E>, Handler<E>, Executor<E> {

	private final CmdRepo<E, I> cmdRepo;

	private E t;


	private Consumer<E> successHook = t -> {
		if (log.isDebugEnabled()) {
			log.debug("Successfully update dataObject of {}", t.getClass().getSimpleName());
		}
	};

	private Consumer<? super Throwable> errorHook = e -> {
		log.error("Failed to update dataObject of {}, errorMsg: {}", t.getClass().getSimpleName(), e.getMessage());
	};

	public EUpdater(CmdRepo<E, I> cmdRepo) {
		this.cmdRepo = cmdRepo;
  }

	@Override
	public Optional<E> execute() {
		doValidate(t, UpdateGroup.class);
		SpringAppUtil.getBean(ValidateExecutor.class).validate(t);
		E updated = null;
		try {
			updated = cmdRepo.updateE(t);
			successHook.accept(updated);
		}
		catch (Exception e) {
			errorHook.accept(e);
		}
		return Optional.ofNullable(updated);
	}

	@Override
	public Executor<E> successHook(Consumer<E> consumer) {
		this.successHook = consumer;
		return this;
	}

	@Override
	public Executor<E> errorHook(Consumer<? super Throwable> consumer) {
		this.errorHook = consumer;
		return this;
	}

	@Override
	public Executor<E> handle(Consumer<E> consumer) {
		Assert.notNull(t, "DataObject must not be null");
		consumer.accept(t);
		return this;
	}

	@Override
	public Handler<E> load(Supplier<E> t) {
		this.t = t.get();
		return this;
	}

	@Override
	public Executor<E> apply(Supplier<E> t) {
		this.t = t.get();
		return this;
	}

}
