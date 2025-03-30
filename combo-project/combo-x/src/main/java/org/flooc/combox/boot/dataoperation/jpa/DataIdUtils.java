package org.flooc.combox.boot.dataoperation.jpa;

import lombok.extern.slf4j.Slf4j;
import org.flooc.combo.common.util.SpringAppUtil;
import org.flooc.combo.serialnumber.SerialNumberService;

/**
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
public final class DataIdUtils {

  private DataIdUtils() {
  }

  public static String nextId() {
    return SpringAppUtil.getBean(SerialNumberService.class).nextBizNo();
  }

}
