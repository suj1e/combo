package org.flooc.combo.mft.codegen.template.biz.application;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.flooc.combo.mft.codegen.core.AbstractTemplateVars;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
@Setter
@SuperBuilder
public class ApplicationTemplateVars extends AbstractTemplateVars {

  private String group;
  private String pkgPrefix;
  private String uPrefix;
  private String prefix;

}