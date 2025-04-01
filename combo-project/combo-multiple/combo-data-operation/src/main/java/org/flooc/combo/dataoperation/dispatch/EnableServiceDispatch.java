package org.flooc.combo.dataoperation.dispatch;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.flooc.combo.dataoperation.dispatch.cmd.EnableCmdServiceDispatch;
import org.flooc.combo.dataoperation.dispatch.query.EnableQueryServiceDispatch;
import org.springframework.core.annotation.AliasFor;

/**
 * @author sujie
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@EnableCmdServiceDispatch
@EnableQueryServiceDispatch
public @interface EnableServiceDispatch {

  @AliasFor(annotation = EnableCmdServiceDispatch.class, attribute = "value")
  String cmdServicesScan();

  @AliasFor(annotation = EnableQueryServiceDispatch.class, attribute = "value")
  String queryServicesScan();


}
