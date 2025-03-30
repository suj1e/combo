package org.flooc.combo.dataoperation.query.filler.support;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.StringUtils;

/**
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
public class AnnotationDataFillEvaluator implements DataFillEvaluator {

	private final ExpressionParser parser = new SpelExpressionParser();

	private final TemplateParserContext templateParserContext = new TemplateParserContext();

	private final BeanResolver beanResolver;

	private DataFillEvaluatorContext context;

	public AnnotationDataFillEvaluator(BeanResolver beanResolver) {
		this.beanResolver = beanResolver;
	}

	@Override
	public void setContext(DataFillEvaluatorContext context) {
		this.context = context;
	}

	@Override
	public BiConsumer<Object, List<Object>> evaluateMatchHook() {
		return new DataElWriter(context.getField(), parser);
	}

	@Override
	public BiConsumer<Object, Object> evaluateUnmatchHook() {
		return (sourceData, sourceKey) -> {
			Field field = context.getField();
			String fieldName = field.getName();
			log.warn("Unable to match sourceData, sourceKey[{}], class[{}], field[{}]", sourceKey,
					context.getConfig().getSimpleName(), fieldName);
		};
	}

	@Override
	public Function<Object, Object> evaluateConvert() {
		DataFillMetadata dataFillMetadata = context.getDataFillMetadata();
		if (StringUtils.hasText(dataFillMetadata.getConvert())) {
			return new DataElReader<>(dataFillMetadata.getConvert(), parser, templateParserContext, beanResolver);
		}
		else {
			return Function.identity();
		}
	}

	@Override
	public Function<List<Object>, List<Object>> evaluateLoad() {
		DataFillMetadata dataFillMetadata = context.getDataFillMetadata();
		return new DataElReader<>(dataFillMetadata.getLoad(), parser, templateParserContext, beanResolver);
	}

	@Override
	public Function<Object, Object> evaluateRelateKey() {
		DataFillMetadata dataFillMetadata = context.getDataFillMetadata();
		return new DataElReader<>(dataFillMetadata.getRelateKey(), parser, templateParserContext, beanResolver);
	}

	@Override
	public Function<Object, Object> evaluateSourceKey() {
		DataFillMetadata dataFillMetadata = context.getDataFillMetadata();
		return new DataElReader<>(dataFillMetadata.getSourceKey(), parser, templateParserContext, beanResolver);
	}

	@Override
	public String evaluateProcessorName() {
		DataFillMetadata dataFillMetadata = context.getDataFillMetadata();
		String nameFormat = "class[%s]-#field[%s]-[%s]";
		return String.format(nameFormat, context.getConfig().getSimpleName(), context.getField().getName(),
				dataFillMetadata.getClass().getSimpleName());
	}

	@Override
	public int evaluateProcessorLevel() {
		DataFillMetadata dataFillMetadata = context.getDataFillMetadata();
		return dataFillMetadata.getProcessLevel();
	}

}
