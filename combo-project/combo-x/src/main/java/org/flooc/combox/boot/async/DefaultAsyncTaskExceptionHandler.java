package org.flooc.combox.boot.async;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
public class DefaultAsyncTaskExceptionHandler implements AsyncTaskExceptionHandler {

  @Override
  public void handle(Exception e) {
    log.error("Failed to execute async task", e);
  }
}
