package org.flooc.combox.boot.async;

/**
 * 异常任务异常处理器
 *
 * @author sujie
 * @since 1.0.0
 */
public interface AsyncTaskExceptionHandler {

  void handle(Exception e);

}
