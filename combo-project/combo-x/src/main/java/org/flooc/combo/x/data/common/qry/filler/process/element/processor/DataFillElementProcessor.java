package org.flooc.combo.x.data.common.qry.filler.process.element.processor;

import java.util.List;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface DataFillElementProcessor {

	<T> void process(List<T> sourceDataList);

	default int processLevel() {
		return 0;
	}

}
