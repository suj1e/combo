package org.flooc.combo.dataoperation.query.filler.process.group.processor;

import java.util.List;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface DataFillGroupProcessor {

	<T> void process(List<T> dataList);

}
