package org.flooc.combo.mft.codegen.template.biz.rpc.provider;


import lombok.Getter;
import org.flooc.combo.mft.codegen.core.TemplateMetadata;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
public enum OpenfeignProviderTemplate implements TemplateMetadata {
  CmdRpcProvider(""),
  QueryRpcProvider(""),
  ;

  private final String subPkg;

  OpenfeignProviderTemplate(String subPkg) {
    this.subPkg = subPkg;
  }



  @Override
  public String templateDir() {
    return "biz/rpc/provider";
  }

  @Override
  public String templateName() {
    return this.name();
  }

}