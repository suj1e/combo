package org.flooc.combo.x.web.startup;

import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;

/**
 * @author sujie
 * @since 1.0.0
 */
public final class SpringAppUtil {

  private SpringAppUtil(){}

  private static ApplicationContext applicationContext;

  public static void setApplicationContext(ApplicationContext applicationContext) {
    SpringAppUtil.applicationContext = applicationContext;
  }

  public static <T> T getBean(@NonNull Class<T> requiredType) {
    return applicationContext.getBean(requiredType);
  }

  public static void publishEvent(Object event) {
    applicationContext.publishEvent(event);
  }


}
