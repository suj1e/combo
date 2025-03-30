package org.flooc.combo.dataoperation.dispatch;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;

/**
 * @author sujie
 * @since 1.0.0
 */
public class ServicesScanningProvider extends
    ClassPathScanningCandidateComponentProvider {


  public ServicesScanningProvider(boolean useDefaultFilters, Class<?> serviceType,
      Class<? extends Annotation> excludeAnnoType, Class<?> excludeExecutorType,
      Class<? extends Annotation> includeAnnoType) {
    super(useDefaultFilters);
    super.addIncludeFilter(
        (metadataReader, metadataReaderFactory) -> Arrays.stream(
                metadataReader.getClassMetadata().getInterfaceNames())
            .anyMatch(i -> serviceType.getName().equals(i)));
    super.addIncludeFilter(new AnnotationTypeFilter(includeAnnoType));
    super.addExcludeFilter(new AnnotationTypeFilter(excludeAnnoType));
    super.addExcludeFilter(new AssignableTypeFilter(excludeExecutorType));
  }


  @Override
  protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
    AnnotationMetadata metadata = beanDefinition.getMetadata();
    return metadata.isIndependent() && metadata.isAbstract();
  }
}
