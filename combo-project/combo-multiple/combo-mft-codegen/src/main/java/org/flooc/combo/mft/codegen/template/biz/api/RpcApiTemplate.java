package org.flooc.combo.mft.codegen.template.biz.api;

import org.flooc.combo.mft.codegen.core.TemplateMetadata;

/**
 * @author sujie
 * @since 1.0.0
 */
public enum RpcApiTemplate implements TemplateMetadata {
  CmdRpcApi,
  QueryRpcApi,
  ;

  @Override
  public String templateDir() {
    return "biz/api";
  }

  @Override
  public String templateName() {
    return this.name();
  }
}

