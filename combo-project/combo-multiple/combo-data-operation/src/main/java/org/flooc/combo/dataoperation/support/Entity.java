package org.flooc.combo.dataoperation.support;

import java.io.Serializable;
import org.flooc.combo.common.util.SpringAppUtil;

/**
 * 实体，聚合根也是实体【聚合根不仅是实体，还可以包含实体、值对象等】
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
