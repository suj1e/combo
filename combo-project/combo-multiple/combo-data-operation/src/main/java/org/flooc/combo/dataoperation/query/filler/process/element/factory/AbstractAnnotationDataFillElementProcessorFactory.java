package org.flooc.combo.dataoperation.query.filler.process.element.factory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import one.util.streamex.StreamEx;
import org.flooc.combo.dataoperation.query.filler.process.element.processor.DataFillElementProcessor;
import org.flooc.combo.common.util.ReflectUtils;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.Assert;

/**
 * @author sujie
 * @since 1.0.0
 */
abstract class AbstractAnnotationDataFillElementProcessorFactory<A extends Annotation>
		implements DataFillElementProcessorFactory {

	private final Class<A> annotationType;

	protected AbstractAnnotationDataFillElementProcessorFactory(Class<A> annotationType) {
		Assert.state(annotationType != null, "AnnotationType must not be null");
		this.annotationType = annotationType;
	}

	@Override
	public List<DataFillElementProcessor> createProcessor(Class<?> config) {
		List<Field> allFields = ReflectUtils.getAllFields(config);
		return StreamEx.of(allFields)
			.filter(f -> AnnotatedElementUtils.hasAnnotation(f, this.annotationType))
			.map(field -> parse(config, field, AnnotatedElementUtils.findMergedAnnotation(field, this.annotationType)))
			.filter(Objects::nonNull)
			.toList();
	}

	protected abstract DataFillElementProcessor parse(Class<?> config, Field field, A annotation);

}
