package org.flooc.combo.dataoperation.validate;

import org.flooc.combo.common.util.SpringAppUtil;

/**
 * @author sujie
 * @since 1.0.0
 */
public final class ValidateUtils {

  private ValidateUtils(){}

  public static <T> void validate(T data) {
    SpringAppUtil.getBean(ValidateExecutor.class).validate(data);
  }

}
