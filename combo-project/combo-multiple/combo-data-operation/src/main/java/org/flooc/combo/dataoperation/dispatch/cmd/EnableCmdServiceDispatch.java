package org.flooc.combo.dataoperation.dispatch.cmd;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

/**
 * @author sujie
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(CmdServiceRegister.class)
public @interface EnableCmdServiceDispatch {

  String value() default "";

  Class<?> factoryBeanType() default CmdServiceFactoryBean.class;

  Class<?> serviceType() default CmdService.class;

  Class<? extends Annotation> excludeAnnoType() default NoCmdService.class;

  Class<? extends Annotation> includeAnnoType() default CmdServiceDefinition.class;

  Class<?> dispatchExecutorType() default CmdServiceExecutor.class;

}
