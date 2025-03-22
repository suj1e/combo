package org.flooc.combo.common.constant;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface AppEnum<T extends Enum<T> & AppEnum<T>> {

  Integer getCode();

  String getText();

  static <T extends Enum<T> & AppEnum<T>> T parse(Class<T> clazz, Integer code) {
    for (T t : clazz.getEnumConstants()) {
      if (t.getCode().intValue() == code.intValue()) {
        return t;
      }
    }
    return null;
  }

}
