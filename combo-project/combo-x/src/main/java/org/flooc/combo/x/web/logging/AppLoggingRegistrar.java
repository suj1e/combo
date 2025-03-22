package org.flooc.combo.x.web.logging;

import java.lang.annotation.Annotation;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.cloud.openfeign.FeignClientSpecification;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

/**
 * @author sujie
 * @since 1.0.0
 */
class AppLoggingRegistrar implements ImportBeanDefinitionRegistrar {

  @Override
  public void registerBeanDefinitions(@NonNull AnnotationMetadata metadata,
      @NonNull BeanDefinitionRegistry registry) {
    AnnotationAttributes attributes = getAttributes(metadata);
    if (attributes == null) {
      return;
    }
    String pkg = String.valueOf(attributes.get("value"));
    if (!StringUtils.hasText(pkg)) {
      String className = metadata.getClassName();
      pkg = className.substring(0, className.lastIndexOf("."));
    }
    BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(
        FeignClientSpecification.class);
    builder.addConstructorArgValue(pkg);
    builder.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    registry.registerBeanDefinition(AppLoggingConfiguration.class.getSimpleName(),
        builder.getBeanDefinition());
  }

  protected AnnotationAttributes getAttributes(AnnotationMetadata metadata) {
    String name = getAnnotationClass().getName();
    return AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(name, true));
  }

  private Class<? extends Annotation> getAnnotationClass() {
    return EnableAppLogging.class;
  }

}
