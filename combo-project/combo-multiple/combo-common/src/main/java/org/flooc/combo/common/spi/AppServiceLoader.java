package org.flooc.combo.common.spi;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.flooc.combo.common.constant.CommonCmpErrorCode;
import org.flooc.combo.common.exception.CmpRuntimeException;

/**
 * Spi服务加载
 *
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
public final class AppServiceLoader {

  private AppServiceLoader() {
  }

  /**
   * key: interface类型 value: 多个impl的类型
   */
  private static final Map<Class<?>, Collection<Class<?>>> SERVICES = new ConcurrentHashMap<>();

  public static <T> Collection<T> load(Class<T> clazz) {
    if (SERVICES.containsKey(clazz)) {
      return newServiceInstances(clazz);
    }
    Collection<T> result = new LinkedHashSet<>();
    ServiceLoader.load(clazz).forEach(t -> {
      result.add(t);
      SERVICES.computeIfAbsent(clazz, k -> new LinkedHashSet<>()).add(t.getClass());
    });
    return result;
  }

  public static <T> Collection<T> newServiceInstances(final Class<T> clazz) {
    return SERVICES.containsKey(clazz) ? newServiceInstancesFromCache(clazz)
        : Collections.emptyList();
  }

  @SuppressWarnings("unchecked")
  private static <T> Collection<T> newServiceInstancesFromCache(Class<T> clazz) {
    Collection<T> result = new LinkedHashSet<>();
    SERVICES.get(clazz).forEach(i -> result.add((T) newServiceInstance(i)));
    return result;
  }

  private static Object newServiceInstance(final Class<?> clazz) {
    try {
      return clazz.getDeclaredConstructor().newInstance();
    } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
             NoSuchMethodException e) {
      log.error("Failed to load service {}", clazz.getName(), e);
      throw new CmpRuntimeException(CommonCmpErrorCode.CommonSpiError);
    }
  }

}
