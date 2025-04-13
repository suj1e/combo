package org.flooc.combo.mft.codegen.template.biz.rpc.client;


import lombok.Getter;
import org.flooc.combo.mft.codegen.core.TemplateMetadata;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
public enum OpenfeignClientTemplate implements TemplateMetadata {
  CmdRpcClient(""),
  QueryRpcClient(""),
  ;

  private final String subPkg;

  OpenfeignClientTemplate(String subPkg) {
    this.subPkg = subPkg;
  }



  @Override
  public String templateDir() {
    return "biz/rpc/client";
  }

  @Override
  public String templateName() {
    return this.name();
  }

}