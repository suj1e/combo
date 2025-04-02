package org.flooc.combo.mft.codegen.core;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
@Setter
@SuperBuilder
public abstract class AbstractTemplateVars implements TemplateVars {

  private String pkg;

  private String cls;

}
