package org.flooc.combo.dataoperation.query.filler.process.group.factory;

import org.flooc.combo.dataoperation.query.filler.process.group.processor.DataFillGroupProcessor;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface DataFillGroupProcessorFactory {

	DataFillGroupProcessor createProcessor(Class<?> config);

}
