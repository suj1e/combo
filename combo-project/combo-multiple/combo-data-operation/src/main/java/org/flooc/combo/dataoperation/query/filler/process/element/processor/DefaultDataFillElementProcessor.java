package org.flooc.combo.dataoperation.query.filler.process.element.processor;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import one.util.streamex.StreamEx;
import org.springframework.util.CollectionUtils;

/**
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
public class DefaultDataFillElementProcessor implements DataFillElementProcessor {

	private final DataFillElementProcessMetadata processMetadata;

	public DefaultDataFillElementProcessor(DataFillElementProcessMetadata processMetadata) {
		this.processMetadata = processMetadata;
	}

	@Override
	public <T> void process(List<T> sourceDataList) {
		// 提取sourceKey
		List<Object> sourceKeys = StreamEx.of(sourceDataList)
			.filter(Objects::nonNull)
			.map(i -> processMetadata.getSourceKey().apply(i))
			.filter(Objects::nonNull)
			.distinct()
			.toList();
		// 获取数据
		List<Object> allElements = processMetadata.getLoad().apply(sourceKeys);
		if (CollectionUtils.isEmpty(allElements)) {
			log.warn("Elements loaded, but it is empty");
			return;
		}
		// 组织 Map
		Map<Object, List<Object>> allElementsMap = StreamEx.of(allElements)
			.filter(Objects::nonNull)
			.groupingBy(i -> processMetadata.getRelateKey().apply(i));

		// 处理每一条sourceData
		StreamEx.of(sourceDataList)
			.filter(i -> Objects.nonNull(processMetadata.getSourceKey().apply(i)))
			.forEachOrdered(sourceData -> {
				Object sourceKey = processMetadata.getSourceKey().apply(sourceData);
				List<Object> matchedElements = allElementsMap.get(sourceKey);
				if (CollectionUtils.isEmpty(matchedElements)) {
					processMetadata.getUnmatchHook().accept(sourceData, sourceKey);
				}
				else {
					List<Object> convertMatchedElements = StreamEx.of(matchedElements)
						.filter(Objects::nonNull)
						.map(matchedElement -> processMetadata.getConvert().apply(matchedElement))
						.toList();
					processMetadata.getMatchHook().accept(sourceData, convertMatchedElements);
				}
			});
	}

}
