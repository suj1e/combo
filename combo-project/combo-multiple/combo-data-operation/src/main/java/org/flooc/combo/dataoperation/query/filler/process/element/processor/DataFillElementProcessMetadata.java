package org.flooc.combo.dataoperation.query.filler.process.element.processor;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import lombok.Builder;
import lombok.Getter;

/**
 * @author sujie
 * @since 1.0.0
 */
@Builder
@Getter
public class DataFillElementProcessMetadata {

	private String name;

	private int processLevel;

	private BiConsumer<Object, List<Object>> matchHook;

	private BiConsumer<Object, Object> unmatchHook;

	private Function<Object, Object> convert;

	private Function<List<Object>, List<Object>> load;

	private Function<Object, Object> relateKey;

	private Function<Object, Object> sourceKey;

}
