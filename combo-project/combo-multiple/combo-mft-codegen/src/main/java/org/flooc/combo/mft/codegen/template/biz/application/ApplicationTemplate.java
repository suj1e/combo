package org.flooc.combo.mft.codegen.template.biz.application;


import org.flooc.combo.mft.codegen.core.TemplateMetadata;

/**
 * @author sujie
 * @since 1.0.0
 */
public enum ApplicationTemplate implements TemplateMetadata {
  AbstractCmd,
  CmdMapper,
  CmdService,
  CmdServiceImpl,
  CreateCmd,
  QueryService,
  QueryServiceImpl,
  UpdateCmd,
  VO,
  VOMapper,
  ;

  @Override
  public String templateDir() {
    return "biz/application";
  }

  @Override
  public String templateName() {
    return this.name();
  }
}