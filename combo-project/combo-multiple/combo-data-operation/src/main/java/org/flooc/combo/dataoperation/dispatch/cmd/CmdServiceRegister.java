package org.flooc.combo.dataoperation.dispatch.cmd;

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
public class CmdServiceRegister extends AbstractServiceRegister {

  @Override
  public String parseBeanName(Class<?> serviceInterface,
      Class<? extends Annotation> includeAnnoType) {
    if (includeAnnoType.isAssignableFrom(CmdServiceDefinition.class)) {
      CmdServiceDefinition cmdServiceDefinition = AnnotatedElementUtils.findMergedAnnotation(
          serviceInterface, CmdServiceDefinition.class);
      if (cmdServiceDefinition != null) {
        String serviceName = cmdServiceDefinition.serviceName();
        if (StringUtils.hasText(serviceName)) {
          return serviceName;
        }
      }
    }
    return serviceInterface.getName();
  }

  @Override
  public Class<? extends Annotation> getAnnotationClass() {
    return EnableCmdServiceDispatch.class;
  }


  @Override
  public void addPropertiesToFactoryBean(BeanDefinitionBuilder builder,Class<?> serviceInterface,
      Class<? extends Annotation> includeAnnoType) {
    if (includeAnnoType.isAssignableFrom(CmdServiceDefinition.class)) {
      CmdServiceDefinition cmdServiceDefinition = AnnotatedElementUtils.findMergedAnnotation(
          serviceInterface, CmdServiceDefinition.class);
      if (cmdServiceDefinition == null) {
        return;
      }
      Class<?> cmdRepoType = cmdServiceDefinition.cmdRepoType();
      Class<?> queryRepoType = cmdServiceDefinition.queryRepoType();
      Class<?> cmdMapperType = cmdServiceDefinition.cmdMapperType();
      builder.addPropertyValue("cmdRepoType", cmdRepoType);
      builder.addPropertyValue("queryRepoType", queryRepoType);
      builder.addPropertyValue("cmdMapperType", cmdMapperType);
    }
  }

}
