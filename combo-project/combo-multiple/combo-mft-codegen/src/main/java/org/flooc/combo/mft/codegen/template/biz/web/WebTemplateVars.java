package org.flooc.combo.mft.codegen.template.biz.web;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.flooc.combo.mft.codegen.core.AbstractTemplateVars;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
@Setter
@SuperBuilder
public class WebTemplateVars extends AbstractTemplateVars {
  private String group;
  private String pkgPrefix;
  private String uPrefix;
  private String prefix;
  private String title;
}