package org.flooc.combo.dataoperation.core;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.flooc.combo.common.util.SpringAppUtil;
import org.flooc.combo.dataoperation.repo.CmdRepo;
import org.flooc.combo.dataoperation.validate.CreateGroup;
import org.flooc.combo.dataoperation.validate.ValidateExecutor;
import org.springframework.util.Assert;

/**
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
public class EntityCreator<P, I> extends AbstractEntityOperation implements Creator<P>, Handler<P>,
    Executor<P> {

  private final CmdRepo<P, I> cmdRepo;

  private P t;


  private Consumer<P> successHook = t -> {
    if (log.isDebugEnabled()) {
      log.debug("Successfully create data of {}", t.getClass().getSimpleName());
    }
  };

  private Consumer<? super Throwable> errorHook = e -> {
    log.error("Failed to create data of {}, errorMsg: {}", t.getClass().getSimpleName(),
        e.getMessage());
  };

  public EntityCreator(CmdRepo<P, I> cmdRepo) {
    this.cmdRepo = cmdRepo;
  }

  @Override
  public Handler<P> create(Supplier<P> t) {
    this.t = t.get();
    return this;
  }

  @Override
  public Optional<P> execute() {
    doValidate(t, CreateGroup.class);
    SpringAppUtil.getBean(ValidateExecutor.class).validate(t);
    P created = null;
    try {
      created = cmdRepo.createEntity(t);
      successHook.accept(created);
    } catch (Exception e) {
      errorHook.accept(e);
    }
    return Optional.ofNullable(created);
  }

  @Override
  public Executor<P> successHook(Consumer<P> consumer) {
    this.successHook = consumer;
    return this;
  }

  @Override
  public Executor<P> errorHook(Consumer<? super Throwable> consumer) {
    this.errorHook = consumer;
    return this;
  }

  @Override
  public Executor<P> handle(Consumer<P> consumer) {
    Assert.notNull(t, "Data must not be null");
    consumer.accept(t);
    return this;
  }

}
