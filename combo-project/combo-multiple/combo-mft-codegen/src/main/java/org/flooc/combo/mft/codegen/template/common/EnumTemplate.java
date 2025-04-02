package org.flooc.combo.mft.codegen.template.common;

import org.flooc.combo.mft.codegen.core.TemplateMetadata;

/**
 * @author sujie
 * @since 1.0.0
 */
public enum EnumTemplate implements TemplateMetadata {
  Enum,
  EnumConverter,
  EnumMapper
  ;

  @Override
  public String templateDir() {
    return "common/enums";
  }

  @Override
  public String templateName() {
    return this.name();
  }
}
