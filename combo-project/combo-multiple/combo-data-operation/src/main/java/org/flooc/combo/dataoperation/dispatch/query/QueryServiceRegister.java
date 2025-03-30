package org.flooc.combo.dataoperation.dispatch.query;

import java.lang.annotation.Annotation;
import lombok.extern.slf4j.Slf4j;
import org.flooc.combo.dataoperation.dispatch.AbstractServiceRegister;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.StringUtils;

/**
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
public class QueryServiceRegister extends AbstractServiceRegister {


  @Override
  public Class<? extends Annotation> getAnnotationClass() {
    return EnableQueryServiceDispatch.class;
  }

  @Override
  public void addPropertiesToFactoryBean(BeanDefinitionBuilder builder,Class<?> serviceInterface,
      Class<? extends Annotation> includeAnnoType) {
    if (includeAnnoType.isAssignableFrom(QueryServiceDefinition.class)) {
      QueryServiceDefinition queryServiceDefinition = AnnotatedElementUtils.findMergedAnnotation(
          serviceInterface, QueryServiceDefinition.class);
      if (queryServiceDefinition == null) {
        return;
      }
      Class<?> queryRepoType = queryServiceDefinition.queryRepoType();
      Class<?> voMapperType = queryServiceDefinition.voMapperType();
      builder.addPropertyValue("queryRepoType", queryRepoType);
      builder.addPropertyValue("voMapperType", voMapperType);
    }
  }

  @Override
  public String parseBeanName(Class<?> serviceInterface,
      Class<? extends Annotation> includeAnnoType) {
    if (includeAnnoType.isAssignableFrom(QueryServiceDefinition.class)) {
      QueryServiceDefinition queryServiceDefinition = AnnotatedElementUtils.findMergedAnnotation(
          serviceInterface, QueryServiceDefinition.class);
      if (queryServiceDefinition != null) {
        String serviceName = queryServiceDefinition.serviceName();
        if (StringUtils.hasText(serviceName)) {
          return serviceName;
        }
      }
    }
    return serviceInterface.getName();
  }

}
