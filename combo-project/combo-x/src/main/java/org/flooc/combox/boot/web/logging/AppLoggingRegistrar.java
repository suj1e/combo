package org.flooc.combox.boot.web.logging;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.NonNull;

/**
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
class AppLoggingRegistrar implements ImportBeanDefinitionRegistrar {

  @Override
  public void registerBeanDefinitions(@NonNull AnnotationMetadata metadata,
      @NonNull BeanDefinitionRegistry registry) {
    AnnotationAttributes attributes = getAttributes(metadata);
    if (attributes == null) {
      return;
    }
    String[] values = attributes.getStringArray("value");
    if (values.length == 0) {
      String className = metadata.getClassName();
      values = new String[]{className.substring(0, className.lastIndexOf("."))};
    }
    if (log.isDebugEnabled()) {
      log.debug("AppLogging has been enabled, the active package is {}", Arrays.toString(values));
    }
    BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(
        AppLoggingConfiguration.class);
    builder.addConstructorArgValue(values);
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
