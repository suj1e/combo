package org.flooc.combo.mft.codegen.template.common;

import lombok.Getter;
import org.flooc.combo.mft.codegen.core.TemplateMetadata;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
public enum EnumTemplate implements TemplateMetadata {
  Enum(""),
  EnumConverter(""),
  EnumMapper("")
  ;

  private final String subPkg;

  EnumTemplate(String subPkg) {
    this.subPkg = subPkg;
  }

  @Override
  public String templateDir() {
    return "common/enums";
  }

  @Override
  public String templateName() {
    return this.name();
  }

}
