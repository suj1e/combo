package org.flooc.combo.common.spi;

/**
 * @author sujie
 * @since 1.0.0
 */
public class ServiceLoadException extends RuntimeException {

  public ServiceLoadException(Class<?> clazz, Exception cause) {
    super(String.format("Failed to load service %s", clazz.getName()), cause);
  }
}
