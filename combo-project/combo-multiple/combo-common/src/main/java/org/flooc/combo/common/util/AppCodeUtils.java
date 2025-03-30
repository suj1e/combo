package org.flooc.combo.common.util;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import org.flooc.combo.common.constant.AppCode;
import org.flooc.combo.common.model.AppCodeModel;

/**
 * @author sujie
 * @since 1.0.0
 */
public final class AppCodeUtils {

  private AppCodeUtils() {
  }

  /**
   * @param clazz 枚举类
   * @param <T> 枚举类型
   * @return 枚举模型
   */
  public static <T extends Enum<T> & AppCode<T>> List<AppCodeModel> apply(Class<T> clazz) {
    return EnumSet.allOf(clazz).stream().map(i -> AppCodeModel.builder()
        .code(i.getCode())
        .name(i.name())
        .text(i.getText())
        .build()).collect(Collectors.toList());
  }

}
