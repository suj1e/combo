package org.flooc.combo.serialnumber;

import lombok.extern.slf4j.Slf4j;
import org.apache.seata.common.util.IdWorker;
import org.flooc.combo.common.util.SpringAppUtil;
import org.springframework.lang.NonNull;

/**
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
public class SeataSerialNumberServiceImpl implements SerialNumberService {

  @Override
  public String nextBizNo() {
    IdWorker idWorker = SpringAppUtil.getBean(IdWorker.class);
    long nextId = idWorker.nextId();
    if (log.isDebugEnabled()) {
      log.debug("IdWorker: {}, nextId:{}", idWorker, nextId);
    }
    return String.valueOf(nextId);
  }

  @Override
  public String nextBizNo(String bizIdentity) {
    IdWorker idWorker = SpringAppUtil.getBean(IdWorker.class);
    long nextId = idWorker.nextId();
    if (log.isDebugEnabled()) {
      log.debug("IdWorker: {}, bizIdentity:{}, nextId:{}", idWorker, bizIdentity, nextId);
    }
    return bizIdentity + nextId;
  }

  @Override
  public boolean supports(@NonNull SerialNumberType delimiter) {
    return SerialNumberType.SEATA_SNOWFLAKE.equals(delimiter);
  }
}
