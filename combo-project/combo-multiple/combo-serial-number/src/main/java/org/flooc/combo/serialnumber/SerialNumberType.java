package org.flooc.combo.serialnumber;

import java.util.Optional;
import lombok.Getter;
import org.flooc.combo.common.constant.AppCode;
import org.flooc.combo.common.constant.BizCode;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
public enum SerialNumberType implements BizCode<SerialNumberType> {

  SEATA_SNOWFLAKE(1, "Seata雪花算法"),
  ;

  private final Integer code;

  private final String text;

  SerialNumberType(Integer code, String text) {
    this.code = code;
    this.text = text;
  }

  public static Optional<SerialNumberType> of(Integer code) {
    return Optional.ofNullable(AppCode.parse(SerialNumberType.class, code));
  }

}