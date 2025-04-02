package org.flooc.combo.mft.codegen.template.biz.domain;


import org.flooc.combo.mft.codegen.core.TemplateMetadata;

/**
 * @author sujie
 * @since 1.0.0
 */
public enum DomainTemplate implements TemplateMetadata {
  Entity,
  EntityFactory,
  DomainService,
  DomainServiceImpl,
  Events,
  PageQuery,
  CmdRepo,
  QueryRepo,
  EventProcessor,
  ;

  @Override
  public String templateDir() {
    return "biz/domain";
  }

  @Override
  public String templateName() {
    return this.name();
  }
}