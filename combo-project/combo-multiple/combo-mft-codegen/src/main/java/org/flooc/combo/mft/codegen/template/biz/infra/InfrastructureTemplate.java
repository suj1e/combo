package org.flooc.combo.mft.codegen.template.biz.infra;

import lombok.Getter;
import org.flooc.combo.mft.codegen.core.TemplateMetadata;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
public enum InfrastructureTemplate implements TemplateMetadata {
  PO("po"),
  POMapper("mapper"),
  JpaCmdRepo("repo"),
  JpaQueryRepo("repo"),
  CmdRepoImpl("repo.impl"),
  QueryRepoImpl("repo.impl")
  ;

  private final String subPkg;

  InfrastructureTemplate(String subPkg) {
    this.subPkg = subPkg;
  }

  @Override
  public String templateDir() {
    return "biz/infrastructure";
  }

  @Override
  public String templateName() {
    return this.name();
  }

}