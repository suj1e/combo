package org.flooc.combo.mft.codegen.template.biz.domain;


import lombok.Getter;
import org.flooc.combo.mft.codegen.core.TemplateMetadata;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
public enum DomainTemplate implements TemplateMetadata {
  Entity("entity"),
  DomainService("domainservice"),
  DomainServiceImpl("domainservice.impl"),
  Events("event"),
  PageQuery("query"),
  CmdRepo("repo"),
  QueryRepo("repo"),
  EventProcessor("event"),
  AbstractVO("vo"),
  VO("vo"),
  VOMapper("mapper"),
  ;
  private final String subPkg;

  DomainTemplate(String subPkg) {
    this.subPkg = subPkg;
  }

  @Override
  public String templateDir() {
    return "biz/domain";
  }

  @Override
  public String templateName() {
    return this.name();
  }

}