package org.flooc.combo.mft.codegen.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;
import lombok.Builder;

/**
 * @author sujie
 * @since 1.0.0
 */
@Builder
public class TemplateRender {

  private String templateName;
  private String templateContent;
  private TemplateVars templateVars;

  public String renderContent() {
    StringWriter stringWriter = new StringWriter();
    Mustache compile = new DefaultMustacheFactory().compile(new StringReader(templateContent),
        templateName);
    ObjectMapper om = new ObjectMapper();
    compile.execute(stringWriter, om.convertValue(templateVars, Map.class));
    return stringWriter.toString();
  }

}
