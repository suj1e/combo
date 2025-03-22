package org.flooc.combo.x.data.common.qry.filler.support;

import java.lang.reflect.Field;
import lombok.Builder;
import lombok.Data;
import org.flooc.combo.x.data.common.qry.filler.support.DataFillMetadata;

/**
 * @author sujie
 * @since 1.0.0
 */
@Data
@Builder
public class DataFillEvaluatorContext {

	private Class<?> config;

	private Field field;

	private DataFillMetadata dataFillMetadata;

}
