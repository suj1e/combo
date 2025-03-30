package org.flooc.combo.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.util.Assert;

/**
 * 反射工具
 *
 * @author sujie
 * @since 1.0.0
 */
public final class ReflectUtils {

  private ReflectUtils() {
  }

  public static List<Field> getAllFields(final Class<?> clazz) {
    Assert.notNull(clazz,"Clazz must not be null");
    final List<Field> allFields = new ArrayList<>();
    Class<?> currentClass = clazz;
    while (currentClass != null) {
      final Field[] declaredFields = currentClass.getDeclaredFields();
      Collections.addAll(allFields, declaredFields);
      currentClass = currentClass.getSuperclass();
    }
    return allFields;
  }

}
