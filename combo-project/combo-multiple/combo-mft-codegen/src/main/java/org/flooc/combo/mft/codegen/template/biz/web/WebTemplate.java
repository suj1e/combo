package org.flooc.combo.mft.codegen.template.biz.web;


import lombok.Getter;
import org.flooc.combo.mft.codegen.core.TemplateMetadata;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
public enum WebTemplate implements TemplateMetadata {
  CmdController(""),
  QueryController(""),
  RequestMapper("mapper"),
  RespMapper("mapper"),
  AbstractRequest("request"),
  CreateRequest("request"),
  UpdateRequest("request"),
  QueryRequest("request"),
  QueryResp("response"),
  ;

  private final String subPkg;

  WebTemplate(String subPkg) {
    this.subPkg = subPkg;
  }



  @Override
  public String templateDir() {
    return "biz/web";
  }

  @Override
  public String templateName() {
    return this.name();
  }

}

