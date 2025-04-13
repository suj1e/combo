package org.flooc.combo.mft.codegen.template.biz.application;


import lombok.Getter;
import org.flooc.combo.mft.codegen.core.TemplateMetadata;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
public enum ApplicationTemplate implements TemplateMetadata {
  AbstractCmd("cmd"),
  CmdMapper("mapper"),
  CmdService("service"),
  CreateCmd("cmd"),
  QueryService("service"),
  UpdateCmd("cmd"),

  ;

  private final String subPkg;

  ApplicationTemplate(String subPkg) {
    this.subPkg = subPkg;
  }

  @Override
  public String templateDir() {
    return "biz/application";
  }

  @Override
  public String templateName() {
    return this.name();
  }

}