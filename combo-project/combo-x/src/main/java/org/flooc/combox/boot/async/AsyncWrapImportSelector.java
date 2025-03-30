package org.flooc.combox.boot.async;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.NonNull;

/**
 * @author sujie
 * @since 1.0.0
 */
public class AsyncWrapImportSelector implements ImportSelector {

  @NonNull
  @Override
  public String[] selectImports(@NonNull AnnotationMetadata importingClassMetadata) {
    return new String[]{AsyncWrapConfiguration.class.getName()};
  }
}
