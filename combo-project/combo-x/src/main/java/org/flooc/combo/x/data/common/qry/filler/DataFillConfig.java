package org.flooc.combo.x.data.common.qry.filler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sujie
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DataFillConfig {

	DataFillProcessType processType() default DataFillProcessType.CONCURRENT;

	String executorName() default "defaultDataFillExecutor";

}
