package org.flooc.combo.x.data.common.support;

import java.io.Serializable;
import org.flooc.combo.x.web.startup.SpringAppUtil;

/**
 * 实体，聚合根也是实体
 *
 * @author sujie
 * @since 1.0.0
 */
public interface Entity<T> extends Serializable {

	T getId();

	default <N> N registerEvent(N event) {
		SpringAppUtil.publishEvent(event);
		return event;
	}

}
