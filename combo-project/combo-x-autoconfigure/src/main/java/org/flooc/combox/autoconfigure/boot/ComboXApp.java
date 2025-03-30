package org.flooc.combox.autoconfigure.boot;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.flooc.combox.boot.async.EnableAsyncWrap;
import org.flooc.combox.boot.web.logging.EnableAppLogging;
import org.springframework.core.annotation.AliasFor;

/**
 * @author sujie
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
@EnableAppLogging
@EnableAsyncWrap
public @interface ComboXApp {

  /**
   * @return 日志扫描包
   */
  @AliasFor(annotation = EnableAppLogging.class, attribute = "basePackages")
  String[] loggingPackages() default {};

}
