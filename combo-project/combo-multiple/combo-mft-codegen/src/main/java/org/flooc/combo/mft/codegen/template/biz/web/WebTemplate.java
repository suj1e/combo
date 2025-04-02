package org.flooc.combo.mft.codegen.template.biz.web;


import org.flooc.combo.mft.codegen.core.TemplateMetadata;

/**
 * @author sujie
 * @since 1.0.0
 */
public enum WebTemplate implements TemplateMetadata {
  CmdController,
  QueryController,
  RequestMapper,
  RespMapper,
  AbstractRequest,
  CreateRequest,
  UpdateRequest,
  QueryRequest,
  QueryResp,
  ;

  @Override
  public String templateDir() {
    return "biz/web";
  }

  @Override
  public String templateName() {
    return this.name();
  }
}

