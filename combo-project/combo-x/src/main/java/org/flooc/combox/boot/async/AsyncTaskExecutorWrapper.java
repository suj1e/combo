package org.flooc.combox.boot.async;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.lang.NonNull;

/**
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
public class AsyncTaskExecutorWrapper implements AsyncTaskExecutor, InitializingBean, DisposableBean {

  private final AsyncTaskExecutor executor;
  private final AsyncTaskExceptionHandler exceptionHandler;

  @Override
  public void destroy() throws Exception {
    if (executor instanceof DisposableBean executorBean) {
      executorBean.destroy();
    }
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    if (executor instanceof InitializingBean executorBean) {
      executorBean.afterPropertiesSet();
    }
  }

  @Override
  public void execute(@NonNull Runnable task) {
    executor.execute(wrapRunnable(task));
  }

  @Override
  @NonNull
  public Future<?> submit(@NonNull Runnable task) {
    return executor.submit(wrapRunnable(task));
  }

  @Override
  @NonNull
  public <T> Future<T> submit(@NonNull Callable<T> task) {
    return executor.submit(wrapCallable(task));
  }

  private <T> Callable<T> wrapCallable(Callable<T> task) {
    return () -> {
      try {
        return task.call();
      } catch (Exception e) {
        exceptionHandler.handle(e);
        throw e;
      }
    };
  }

  private Runnable wrapRunnable(Runnable task) {
    return () -> {
      try {
        task.run();
      } catch (Exception e) {
        exceptionHandler.handle(e);
        throw e;
      }
    };
  }




}
