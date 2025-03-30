package org.flooc.combo.sms;

import java.util.Optional;
import lombok.Getter;
import org.flooc.combo.common.constant.AppCode;
import org.flooc.combo.common.constant.BizCode;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
public enum SmsProducerType implements BizCode<SmsProducerType> {
  ALI(1, "阿里"), VOLC(2, "火山引擎"),;

  private final Integer code;

  private final String text;

  SmsProducerType(Integer code, String text) {
    this.code = code;
    this.text = text;
  }

  public static Optional<SmsProducerType> of(Integer code) {
    return Optional.ofNullable(AppCode.parse(SmsProducerType.class, code));
  }

}
