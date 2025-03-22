package org.flooc.combo.x.data.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.util.Assert;

/**
 * @author sujie
 * @since 1.0.0
 */
public final class ReflectUtils {

	private ReflectUtils() {
	}

	public static List<Field> getAllFields(final Class<?> clazz) {
		Assert.notNull(clazz, "Class must not be null");
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
