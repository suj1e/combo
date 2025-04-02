package org.flooc.combo.mft.codegen.template.biz.rpc.client;


import org.flooc.combo.mft.codegen.core.TemplateMetadata;

/**
 * @author sujie
 * @since 1.0.0
 */
public enum OpenfeignClientTemplate implements TemplateMetadata {
  CmdRpcClient,
  QueryRpcClient,
  ;

  @Override
  public String templateDir() {
    return "biz/rpc/client";
  }

  @Override
  public String templateName() {
    return this.name();
  }
}