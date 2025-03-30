package org.flooc.combox.boot.web.startup;

import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 应用启动监听器
 *
 * @author sujie
 * @see SpringApplicationRunListener
 * @since 1.0.0
 */
public interface AppRunListener {

  default void starting() {
  }

  /**
   * @param environment 环境
   */
  default void environmentPrepared(ConfigurableEnvironment environment) {
  }

  /**
   * @param context 上下文
   */
  default void contextPrepared(ConfigurableApplicationContext context) {
  }

  /**
   * @param context 上下文
   */
  default void contextLoaded(ConfigurableApplicationContext context) {
  }

  /**
   * @param context 上下文
   */
  default void started(ConfigurableApplicationContext context) {
  }

  /**
   * @param context 上下文
   * @param exception 异常
   */
  default void failed(ConfigurableApplicationContext context, Throwable exception) {
  }

}
