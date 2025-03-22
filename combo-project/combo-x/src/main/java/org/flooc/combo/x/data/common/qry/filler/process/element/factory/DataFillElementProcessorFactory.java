package org.flooc.combo.x.data.common.qry.filler.process.element.factory;

import java.util.List;
import org.flooc.combo.x.data.common.qry.filler.process.element.processor.DataFillElementProcessor;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface DataFillElementProcessorFactory {

	List<DataFillElementProcessor> createProcessor(Class<?> config);

}
