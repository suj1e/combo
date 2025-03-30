package org.flooc.combo.dataoperation.query.filler.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.flooc.combo.dataoperation.query.filler.DataFillService;
import org.flooc.combo.dataoperation.query.filler.process.group.factory.DataFillGroupProcessorFactory;
import org.flooc.combo.dataoperation.query.filler.process.group.processor.DataFillGroupProcessor;

/**
 * @author sujie
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class DefaultDataFillService implements DataFillService {

	private final DataFillGroupProcessorFactory dataFillGroupProcessorFactory;

	private final Map<Class<?>, DataFillGroupProcessor> CACHE = new ConcurrentHashMap<>();

	@Override
	public <T> void fill(Class<T> config, List<T> dataList) {
		CACHE.computeIfAbsent(config, dataFillGroupProcessorFactory::createProcessor).process(dataList);
	}

	@Override
	public <T> void registerConfig(Class<T> config) {
		CACHE.put(config, dataFillGroupProcessorFactory.createProcessor(config));
	}

}
