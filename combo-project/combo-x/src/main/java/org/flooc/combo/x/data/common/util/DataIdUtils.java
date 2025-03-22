package org.flooc.combo.x.data.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.seata.common.util.IdWorker;
import org.flooc.combo.x.web.startup.SpringAppUtil;

/**
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
public final class DataIdUtils {

	public static String nextId() {
		IdWorker idWorker = SpringAppUtil.getBean(IdWorker.class);
		long nextId = idWorker.nextId();
		if (log.isDebugEnabled()) {
			log.debug("IdWorker: {}, nextId:{}", idWorker, nextId);
		}
		return String.valueOf(nextId);
	}

}
