package org.flooc.combo.dataoperation.dispatch.query;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

/**
 * @author sujie
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(QueryServiceRegister.class)
public @interface EnableQueryServiceDispatch {

  String value() default "";
  Class<?> factoryBeanType() default QueryServiceFactoryBean.class;
  Class<?> serviceType() default QueryService.class;
  Class<? extends Annotation> excludeAnnoType() default NoQueryService.class;
  Class<? extends Annotation> includeAnnoType() default QueryServiceDefinition.class;
  Class<?> dispatchExecutorType() default QueryServiceExecutor.class;

}
