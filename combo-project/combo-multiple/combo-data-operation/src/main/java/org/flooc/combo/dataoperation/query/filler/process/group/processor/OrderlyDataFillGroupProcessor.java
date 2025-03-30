package org.flooc.combo.dataoperation.query.filler.process.group.processor;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import one.util.streamex.StreamEx;
import org.flooc.combo.dataoperation.query.filler.process.element.processor.DataFillElementProcessor;
import org.springframework.util.StopWatch;

/**
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
public class OrderlyDataFillGroupProcessor extends AbstractDataFillGroupProcessor {

	public OrderlyDataFillGroupProcessor(Class<?> config, List<DataFillElementProcessor> processors) {
		super(config, processors);
	}

	@Override
	public <T> void process(List<T> dataList) {
		StreamEx.of(this.processors).forEach(processor -> {
			if (log.isDebugEnabled()) {
				log.debug("DataFill start, processor:{}, level:{}", processor, processor.processLevel());
			}
			StopWatch watch = new StopWatch();
			watch.start();
			processor.process(dataList);
			watch.stop();
			if (log.isDebugEnabled()) {
				log.debug("DataFill end, processor:{}, level:{}, cost: {}ms", processor, processor.processLevel(),
						watch.getTotalTimeMillis());
			}
		});
	}

}
