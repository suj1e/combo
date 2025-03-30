package org.flooc.combo.common.model;

import lombok.Builder;
import lombok.Getter;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
@Builder
public class AppCodeModel {

  private Integer code;

  private String name;

  private String text;

}
