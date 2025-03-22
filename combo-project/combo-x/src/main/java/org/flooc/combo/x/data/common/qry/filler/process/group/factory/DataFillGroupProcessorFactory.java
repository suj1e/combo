package org.flooc.combo.x.data.common.qry.filler.process.group.factory;

import org.flooc.combo.x.data.common.qry.filler.process.group.processor.DataFillGroupProcessor;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface DataFillGroupProcessorFactory {

	DataFillGroupProcessor createProcessor(Class<?> config);

}
