package org.flooc.combo.dataoperation.query.filler.process.group.processor;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import lombok.extern.slf4j.Slf4j;
import one.util.streamex.StreamEx;
import org.flooc.combo.dataoperation.query.filler.process.element.processor.DataFillElementProcessor;
import org.springframework.util.StopWatch;

/**
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
public class ConcurrentDataFillGroupProcessor extends AbstractDataFillGroupProcessor {

	private final Executor executor;

	private final List<DataFillElementProcessors> processorsList;

	public ConcurrentDataFillGroupProcessor(Class<?> config, List<DataFillElementProcessor> processors,
			Executor executor) {
		super(config, processors);
		this.executor = executor;
		this.processorsList = buildProcessorsList();
	}

	private List<DataFillElementProcessors> buildProcessorsList() {
		return StreamEx.of(this.processors)
			.groupingBy(DataFillElementProcessor::processLevel)
			.entrySet()
			.stream()
			.map(entry -> new DataFillElementProcessors(entry.getKey(), entry.getValue()))
			.sorted(Comparator.comparingInt(DataFillElementProcessors::level))
			.toList();
	}

	@Override
	public <T> void process(List<T> dataList) {
		StreamEx.of(this.processorsList).forEach(i -> {
			if (log.isDebugEnabled()) {
				log.debug("DataFill start, processor:{}, level:{}", i, i.level());
			}
			StopWatch watch = new StopWatch();
			watch.start();
			List<CompletableFuture<Void>> futures = StreamEx.of(i.processors())
				.map(processor -> CompletableFuture.runAsync(() -> processor.process(dataList), executor))
				.toList();
			CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new)).join();
			watch.stop();
			if (log.isDebugEnabled()) {
				log.debug("DataFill end, processor:{}, level:{}, cost: {}ms", i, i.level(), watch.getTotalTimeMillis());
			}
		});
	}

	record DataFillElementProcessors(int level, List<DataFillElementProcessor> processors) {

	}

}
