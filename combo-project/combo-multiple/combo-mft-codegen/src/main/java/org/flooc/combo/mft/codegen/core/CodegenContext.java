package org.flooc.combo.mft.codegen.core;

import java.util.Map;

/**
 * @author sujie
 * @since 1.0.0
 */
public record CodegenContext(TemplateVars templateVars, TemplateMetadata templateMetadata,
                             Map<ExtMapKey, Object> extMap) {

}
