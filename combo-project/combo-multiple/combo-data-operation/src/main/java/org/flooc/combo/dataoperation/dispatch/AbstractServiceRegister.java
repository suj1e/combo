package org.flooc.combo.dataoperation.dispatch;

import java.lang.annotation.Annotation;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

/**
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
public abstract class AbstractServiceRegister implements ImportBeanDefinitionRegistrar {

  @Override
  public void registerBeanDefinitions(@NonNull AnnotationMetadata metadata,
      @NonNull BeanDefinitionRegistry registry) {
    AnnotationAttributes attributes = getAttributes(metadata);
    if (attributes == null) {
      return;
    }
    String basePackage = attributes.getString("value");
    if (!StringUtils.hasText(basePackage)) {
      String className = metadata.getClassName();
      basePackage = className.substring(0, className.lastIndexOf("."));
    }
    if (!basePackage.endsWith("application")) {
      basePackage = basePackage + ".application";
    }
    Class<?> factoryBeanType = attributes.getClass("factoryBeanType");
    Class<?> serviceType = attributes.getClass("serviceType");
    Class<? extends Annotation> excludeAnnoType = attributes.getClass("excludeAnnoType");
    Class<? extends Annotation> includeAnnoType = attributes.getClass("includeAnnoType");
    Class<?> dispatchExecutorType = attributes.getClass("dispatchExecutorType");
    ServicesScanningProvider scanner = new ServicesScanningProvider(false, serviceType,
        excludeAnnoType, dispatchExecutorType, includeAnnoType);
    Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents(basePackage);
    for (BeanDefinition candidate : candidateComponents) {
      try {
        Class<?> serviceInterface = Class.forName(candidate.getBeanClassName());
        if (serviceInterface.isInterface()) {
          BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(
              factoryBeanType);
          builder.addConstructorArgValue(serviceInterface);
          addPropertiesToFactoryBean(builder, serviceInterface, includeAnnoType);
          String beanName = parseBeanName(serviceInterface, includeAnnoType);
          registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
          if (log.isDebugEnabled()) {
            log.debug("Successfully to create service bean: {}", beanName);
          }
        }
      } catch (Exception e) {
        log.error("Failed to create service", e);
      }
    }
  }

  public abstract String parseBeanName(Class<?> serviceInterface,
      Class<? extends Annotation> includeAnnoType);


  protected AnnotationAttributes getAttributes(AnnotationMetadata metadata) {
    String name = getAnnotationClass().getName();
    return AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(name, false));
  }

  public abstract Class<? extends Annotation> getAnnotationClass();

  public abstract void addPropertiesToFactoryBean(BeanDefinitionBuilder builder,
      Class<?> serviceInterface,
      Class<? extends Annotation> includeAnnoType);

}
