package org.flooc.combo.mft.codegen.template.biz.rpc.provider;


import org.flooc.combo.mft.codegen.core.TemplateMetadata;

/**
 * @author sujie
 * @since 1.0.0
 */
public enum OpenfeignProviderTemplate implements TemplateMetadata {
  CmdRpcProvider,
  QueryRpcProvider,
  ;

  @Override
  public String templateDir() {
    return "biz/rpc/provider";
  }

  @Override
  public String templateName() {
    return this.name();
  }
}