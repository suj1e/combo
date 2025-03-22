package org.flooc.combo.x.sms.support;

import java.util.Optional;
import lombok.Getter;
import org.flooc.combo.common.constant.AppEnum;
import org.flooc.combo.common.constant.BizEnum;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
public enum SmsBizType implements BizEnum<SmsBizType> {
  ALI(1, "阿里"), VOLC(2, "火山引擎"),;

  private final Integer code;

  private final String text;

  SmsBizType(Integer code, String text) {
    this.code = code;
    this.text = text;
  }

  public static Optional<SmsBizType> of(Integer code) {
    return Optional.ofNullable(AppEnum.parse(SmsBizType.class, code));
  }

}
