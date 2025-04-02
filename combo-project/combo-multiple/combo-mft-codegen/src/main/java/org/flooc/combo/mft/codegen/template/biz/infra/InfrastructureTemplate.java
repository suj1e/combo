package org.flooc.combo.mft.codegen.template.biz.infra;

import org.flooc.combo.mft.codegen.core.TemplateMetadata;

/**
 * @author sujie
 * @since 1.0.0
 */
public enum InfrastructureTemplate implements TemplateMetadata {
  PO,
  POMapper,
  JpaCmdRepo,
  JpaQueryRepo,
  CmdRepoImpl,
  QueryRepoImpl
  ;

  @Override
  public String templateDir() {
    return "biz/infrastructure";
  }

  @Override
  public String templateName() {
    return this.name();
  }
}