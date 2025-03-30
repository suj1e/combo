package org.flooc.combo.dataoperation.query.filler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sujie
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.FIELD })
public @interface DataFill {

	String convert() default "";

	String load();

	String relateKey();

	String sourceKey() default "";

	int processLevel() default 0;

}
