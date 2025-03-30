package org.flooc.combo.dataoperation.query.filler.support;

import java.lang.reflect.Field;
import lombok.Builder;
import lombok.Getter;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
@Builder
public class DataFillEvaluatorContext {

	private Class<?> config;

	private Field field;

	private DataFillMetadata dataFillMetadata;

}
