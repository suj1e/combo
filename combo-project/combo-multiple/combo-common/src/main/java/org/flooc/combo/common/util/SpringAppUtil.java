package org.flooc.combo.common.util;

import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;

/**
 * Spring应用工具
 *
 * @author sujie
 * @since 1.0.0
 */
public final class SpringAppUtil {

  private SpringAppUtil() {
  }

  private static ApplicationContext applicationContext;

  public static void setApplicationContext(ApplicationContext applicationContext) {
    SpringAppUtil.applicationContext = applicationContext;
  }

  public static <T> T getBean(@NonNull Class<T> requiredType) {
    return applicationContext.getBean(requiredType);
  }

  public static Object getBean(@NonNull String beanName) {
    return applicationContext.getBean(beanName);
  }

  public static <T> Map<String, T> getBeansOfType(@NonNull Class<T> requiredType) {
    return applicationContext.getBeansOfType(requiredType);
  }

  public static void publishEvent(@NonNull Object event) {
    applicationContext.publishEvent(event);
  }

  public static String getProperty(@NonNull String key) {
    return applicationContext.getEnvironment().getProperty(key);
  }


}
