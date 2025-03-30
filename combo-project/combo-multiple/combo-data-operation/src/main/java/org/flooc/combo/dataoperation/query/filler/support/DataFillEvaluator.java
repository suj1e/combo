package org.flooc.combo.dataoperation.query.filler.support;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface DataFillEvaluator {

	void setContext(DataFillEvaluatorContext context);

	BiConsumer<Object, List<Object>> evaluateMatchHook();

	BiConsumer<Object, Object> evaluateUnmatchHook();

	Function<Object, Object> evaluateConvert();

	Function<List<Object>, List<Object>> evaluateLoad();

	Function<Object, Object> evaluateRelateKey();

	Function<Object, Object> evaluateSourceKey();

	String evaluateProcessorName();

	int evaluateProcessorLevel();

}
