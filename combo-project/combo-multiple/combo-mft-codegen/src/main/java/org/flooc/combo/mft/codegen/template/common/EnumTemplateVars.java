package org.flooc.combo.mft.codegen.template.common;

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
public class EnumTemplateVars extends AbstractTemplateVars {

  private String enumType;
  private String enumTypeFull;

}
