package org.flooc.combo.x.data.common.qry.filler.process.group.processor;

import java.util.List;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface DataFillGroupProcessor {

	<T> void process(List<T> dataList);

}
