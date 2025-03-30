package org.flooc.combo.common.constant;

/**
 * 全局应用码
 *
 * @param <T> 枚举类型
 * @author sujie
 * @since 1.0.0
 */
public interface AppCode<T extends Enum<T> & AppCode<T>> {

  /**
   * @return 编码
   */
  Integer getCode();

  /**
   * @return 文本
   */
  String getText();

  static <T extends Enum<T> & AppCode<T>> T parse(Class<T> clazz, Integer code) {
    if (code == null) {
      return null;
    }
    for (T t : clazz.getEnumConstants()) {
      if (t.getCode().intValue() == code.intValue()) {
        return t;
      }
    }
    return null;
  }

}
