package org.flooc.combo.mft.codegen.template.biz.api;

import lombok.Getter;
import org.flooc.combo.mft.codegen.core.TemplateMetadata;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
public enum RpcApiTemplate implements TemplateMetadata {
  CmdRpcApi(""),
  QueryRpcApi(""),
  ;

  private final String subPkg;

  RpcApiTemplate(String subPkg) {
    this.subPkg = subPkg;
  }

  @Override
  public String templateDir() {
    return "biz/api";
  }

  @Override
  public String templateName() {
    return this.name();
  }

}

