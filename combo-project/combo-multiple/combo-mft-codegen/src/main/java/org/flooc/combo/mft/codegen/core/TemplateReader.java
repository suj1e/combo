package org.flooc.combo.mft.codegen.core;

import java.io.IOException;
import jodd.util.StringTemplateParser;
import lombok.Builder;
import org.flooc.combo.mft.codegen.util.TemplateUtils;

/**
 * @author sujie
 * @since 1.0.0
 */
@Builder
public class TemplateReader {

  private TemplateMetadata templateMetadata;

  private static final String TEMPLATE_PATH_FORMAT = "templates/${dir}/${name}.mustache";

  public String readTemplate() throws IOException {
    TemplateMap templateMap = new TemplateMap(templateMetadata.templateDir(),
        templateMetadata.templateName());
    String path = StringTemplateParser.ofBean(templateMap).apply(TEMPLATE_PATH_FORMAT);
    return TemplateUtils.read(path);
  }

  record TemplateMap(String dir, String name) {

  }

}
