package org.flooc.combox.boot.oss;

import java.util.Optional;
import lombok.Getter;
import org.flooc.combo.common.constant.AppCode;
import org.flooc.combo.common.constant.BizCode;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
public enum OssServiceType implements BizCode<OssServiceType> {
  ALIYUN(1,"阿里云")
  ;

  private final Integer code;

  private final String text;

  OssServiceType(Integer code, String text) {
    this.code = code;
    this.text = text;
  }

  public static Optional<OssServiceType> of(Integer code) {
    return Optional.ofNullable(AppCode.parse(OssServiceType.class, code));
  }

}
