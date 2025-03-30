package org.flooc.combo.dataoperation.query.filler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.flooc.combo.common.enums.ExecutorType;

/**
 * @author sujie
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DataFillConfig {

	ExecutorType processType() default ExecutorType.CONCURRENT;

	String executorName() default "defaultDataFillExecutor";

}
