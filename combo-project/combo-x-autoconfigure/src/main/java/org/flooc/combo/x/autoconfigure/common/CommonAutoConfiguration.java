package org.flooc.combo.x.autoconfigure.common;

import org.flooc.combo.x.async.AsyncTaskExceptionHandler;
import org.flooc.combo.x.async.DefaultAsyncTaskExceptionHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * @author sujie
 * @since 1.0.0
 */
@AutoConfiguration
public class CommonAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public AsyncTaskExceptionHandler asyncTaskExceptionHandler() {
    return new DefaultAsyncTaskExceptionHandler();
  }


}
