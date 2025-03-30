package org.flooc.combox.boot.async;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;

/**
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
public final class AsyncTaskCombiner {

  private AsyncTaskCombiner() {
  }

  /**
   * CompletableFuture方式
   *
   * @param tasks    所有任务
   * @param executor 线程池
   */
  public static void joinAllTask(String taskName, List<Runnable> tasks,
      ThreadPoolExecutor executor) {
    if (CollectionUtils.isEmpty(tasks)) {
      return;
    }
    Runnable watchTask = () -> {
      List<CompletableFuture<Void>> futureList = tasks.stream()
          .map(i -> CompletableFuture.runAsync(i, executor))
          .toList();
      CompletableFuture.allOf(futureList.toArray(CompletableFuture[]::new)).join();
    };
    watch(taskName, watchTask, tasks.size());
  }

  /**
   * CountDownLatch方式
   *
   * @param taskName 任务名称
   * @param tasks    所有任务
   * @param executor 线程池
   */
  public static void latchAllTask(String taskName, List<LatchTask> tasks,
      ThreadPoolExecutor executor) {
    if (CollectionUtils.isEmpty(tasks)) {
      return;
    }
    Runnable watchTask = () -> {
      CountDownLatch latch = new CountDownLatch(tasks.size());
      for (LatchTask task : tasks) {
        executor.submit(task);
      }
      try {
        latch.await();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    };
    watch(taskName, watchTask, tasks.size());
  }

  private static void watch(String taskName, Runnable watchTask, int subTaskSize) {
    StopWatch watch = new StopWatch();
    watch.start();
    watchTask.run();
    watch.stop();
    log.info("All {} of {}'s subtasks have been executed, it takes: {}ms", subTaskSize, taskName,
        watch.getTotalTimeMillis());
  }

  public abstract static class LatchTask implements Runnable {

    private final CountDownLatch latch;

    public LatchTask(CountDownLatch latch) {
      this.latch = latch;
    }

    @Override
    public void run() {
      execute();
      latch.countDown();
    }

    abstract void execute();

  }

}
