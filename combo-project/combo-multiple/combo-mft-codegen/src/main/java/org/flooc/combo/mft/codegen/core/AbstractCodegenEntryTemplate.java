package org.flooc.combo.mft.codegen.core;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Map;
import jodd.util.StringUtil;

/**
 * @author sujie
 * @since 1.0.0
 */
public abstract class AbstractCodegenEntryTemplate implements CodegenEntry {

  @Override
  public void generate(CodegenContext context) throws IOException {
    TemplateMetadata templateMetadata = context.templateMetadata();
    String templateContent = TemplateReader.builder().templateMetadata(templateMetadata).build()
        .readTemplate();
    Preconditions.checkArgument(StringUtil.isNotEmpty(templateContent));
    String content = TemplateRender
        .builder()
        .templateContent(templateContent)
        .templateVars(context.templateVars())
        .templateName(templateMetadata.templateName())
        .build()
        .renderContent();
    Preconditions.checkArgument(StringUtil.isNotEmpty(content));
    doGenerate(content, context.extMap());
  }

  public abstract void doGenerate(String content, Map<ExtMapKey, Object> extMap);
}
