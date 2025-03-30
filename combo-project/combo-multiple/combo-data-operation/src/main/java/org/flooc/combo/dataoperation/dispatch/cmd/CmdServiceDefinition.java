package org.flooc.combo.dataoperation.dispatch.cmd;

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
public @interface CmdServiceDefinition {

  String serviceName() default "";

  Class<?> queryRepoType();

  Class<?> cmdRepoType();

  Class<?> cmdMapperType();

}
