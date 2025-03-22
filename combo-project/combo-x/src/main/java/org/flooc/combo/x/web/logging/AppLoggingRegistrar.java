package org.flooc.combo.x.web.logging;

import java.util.Map;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.cloud.openfeign.FeignClientSpecification;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.NonNull;

/**
 * @author sujie
 * @since 1.0.0
 */
class AppLoggingRegistrar implements ImportBeanDefinitionRegistrar {

  @Override
  public void registerBeanDefinitions(AnnotationMetadata metadata,
      @NonNull BeanDefinitionRegistry registry) {
    Map<String, Object> attrs = metadata.getAnnotationAttributes(EnableAppLogging.class.getName(),
        true);
    if (attrs == null) {
      return;
    }
    String value = String.valueOf(attrs.get("value"));
    BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(
        FeignClientSpecification.class);
    builder.addConstructorArgValue(value);
    builder.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    registry.registerBeanDefinition(AppLoggingConfiguration.class.getSimpleName(),
        builder.getBeanDefinition());
  }

}
