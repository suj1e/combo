package org.flooc.combo.x.data.common.operation;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.flooc.combo.common.operation.Creator;
import org.flooc.combo.common.operation.Executor;
import org.flooc.combo.common.operation.Handler;
import org.flooc.combo.x.data.common.repo.CmdRepo;
import org.flooc.combo.x.validate.CreateGroup;
import org.flooc.combo.x.validate.ValidateExecutor;
import org.flooc.combo.x.web.startup.SpringAppUtil;
import org.springframework.util.Assert;

/**
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
public class ECreator<E, I> extends AbstractEOperation implements Creator<E>, Handler<E>,
		Executor<E> {

	private final CmdRepo<E, I> cmdRepo;

	private E t;


	private Consumer<E> successHook = t -> {
		if (log.isDebugEnabled()) {
			log.debug("Successfully create dataObject of {}", t.getClass().getSimpleName());
		}
	};

	private Consumer<? super Throwable> errorHook = e -> {
		log.error("Failed to create dataObject of {}, errorMsg: {}", t.getClass().getSimpleName(), e.getMessage());
	};

	public ECreator(CmdRepo<E, I> cmdRepo) {
		this.cmdRepo = cmdRepo;
	}

	@Override
	public Handler<E> create(Supplier<E> t) {
		this.t = t.get();
		return this;
	}

	@Override
	public Optional<E> execute() {
		doValidate(t, CreateGroup.class);
		SpringAppUtil.getBean(ValidateExecutor.class).validate(t);
		E created = null;
		try {
			created = cmdRepo.createE(t);
			successHook.accept(created);
		}
		catch (Exception e) {
			errorHook.accept(e);
		}
		return Optional.ofNullable(created);
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

}
