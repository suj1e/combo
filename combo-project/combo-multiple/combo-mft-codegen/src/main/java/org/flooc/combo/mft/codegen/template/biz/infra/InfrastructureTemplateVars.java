package org.flooc.combo.mft.codegen.template.biz.infra;

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
public class InfrastructureTemplateVars extends AbstractTemplateVars {

  private String group;
  private String pkgPrefix;
  private String upperPrefix;
  private String prefix;
}