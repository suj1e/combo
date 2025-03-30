package org.flooc.combox.autoconfigure.boot.async;

import org.flooc.combox.boot.async.AsyncTaskExceptionHandler;
import org.flooc.combox.boot.async.DefaultAsyncTaskExceptionHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @author sujie
 * @since 1.0.0
 */
@AutoConfiguration
public class AsyncAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public AsyncTaskExceptionHandler defaultAsyncTaskExceptionHandler() {
    return new DefaultAsyncTaskExceptionHandler();
  }


}
