package org.flooc.combo.dataoperation.query.filler.process.element.factory;

import java.lang.reflect.Field;
import org.flooc.combo.dataoperation.query.filler.DataFill;
import org.flooc.combo.dataoperation.query.filler.process.element.processor.DataFillElementProcessMetadata;
import org.flooc.combo.dataoperation.query.filler.process.element.processor.DataFillElementProcessor;
import org.flooc.combo.dataoperation.query.filler.process.element.processor.DefaultDataFillElementProcessor;
import org.flooc.combo.dataoperation.query.filler.support.AnnotationDataFillEvaluator;
import org.flooc.combo.dataoperation.query.filler.support.DataFillEvaluator;
import org.flooc.combo.dataoperation.query.filler.support.DataFillEvaluatorContext;
import org.flooc.combo.dataoperation.query.filler.support.DataFillMetadata;
import org.springframework.expression.BeanResolver;

/**
 * @author sujie
 * @since 1.0.0
 */
public class DefaultDataFillElementProcessorFactory
		extends AbstractAnnotationDataFillElementProcessorFactory<DataFill> {

	private final BeanResolver beanResolver;

	public DefaultDataFillElementProcessorFactory(BeanResolver beanResolver) {
		super(DataFill.class);
		this.beanResolver = beanResolver;
	}

	@Override
	protected DataFillElementProcessor parse(Class<?> config, Field field, DataFill annotation) {
		DataFillEvaluatorContext context = DataFillEvaluatorContext.builder()
			.config(config)
			.field(field)
			.dataFillMetadata(DataFillMetadata.fromAnnotation(annotation))
			.build();
		DataFillEvaluator evaluator = new AnnotationDataFillEvaluator(beanResolver);
		evaluator.setContext(context);
		DataFillElementProcessMetadata processMetadata = DataFillElementProcessMetadata.builder()
			.name(evaluator.evaluateProcessorName())
			.processLevel(evaluator.evaluateProcessorLevel())
			.matchHook(evaluator.evaluateMatchHook())
			.unmatchHook(evaluator.evaluateUnmatchHook())
			.convert(evaluator.evaluateConvert())
			.load(evaluator.evaluateLoad())
			.relateKey(evaluator.evaluateRelateKey())
			.sourceKey(evaluator.evaluateSourceKey())
			.build();
		return new DefaultDataFillElementProcessor(processMetadata);
	}

}
