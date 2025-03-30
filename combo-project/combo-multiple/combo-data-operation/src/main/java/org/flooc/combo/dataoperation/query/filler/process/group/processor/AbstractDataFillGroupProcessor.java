package org.flooc.combo.dataoperation.query.filler.process.group.processor;

import java.util.Comparator;
import java.util.List;
import org.flooc.combo.dataoperation.query.filler.process.element.processor.DataFillElementProcessor;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * @author sujie
 * @since 1.0.0
 */
abstract class AbstractDataFillGroupProcessor implements DataFillGroupProcessor {

	protected final Class<?> config;

	protected final List<DataFillElementProcessor> processors;

	protected AbstractDataFillGroupProcessor(Class<?> config, List<DataFillElementProcessor> processors) {
		Assert.state(config != null, "DataFillConfig type must not be null");
		Assert.state(!CollectionUtils.isEmpty(processors), "DataFillElementProcessor list must not be empty");

		this.config = config;
		this.processors = processors;
		processors.sort(Comparator.comparingInt(DataFillElementProcessor::processLevel));
	}

}
