package org.flooc.combo.mft.codegen;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.flooc.combo.mft.codegen.core.CodegenContext;
import org.flooc.combo.mft.codegen.core.CodegenEntryType;
import org.flooc.combo.mft.codegen.core.CodegenExecutor;
import org.flooc.combo.mft.codegen.core.DefaultCodegenEntry;
import org.flooc.combo.mft.codegen.template.common.EnumTemplate;
import org.flooc.combo.mft.codegen.template.common.EnumTemplateVars;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author sujie
 * @since 1.0.0
 */
class CodegenTest {

  @BeforeEach
  void setup() {
  }

  @Test
  void testNormalReadTemplate() throws IOException {
    String path = "templates/common/enums/Enum.mustache";
    try (InputStream inputStream = Thread.currentThread().getContextClassLoader()
        .getResourceAsStream(path)) {
      Preconditions.checkNotNull(inputStream);
      String templateContent = new String(inputStream.readAllBytes());
      System.out.println(templateContent);
    }
  }

  @Test
  void testConvertMap() throws IOException {
    String path = "templates/common/enums/Enum.mustache";
    String templateContent = "";
    try (InputStream inputStream = Thread.currentThread().getContextClassLoader()
        .getResourceAsStream(path)) {
      Preconditions.checkNotNull(inputStream);
      templateContent = new String(inputStream.readAllBytes());
    }
    EnumTemplateVars build = EnumTemplateVars.builder().pkg("org.example.demo")
        .cls("HelloType")
        .enumType("HelloType")
        .enumTypeFull("org.example.demo.HelloType").build();
    ObjectMapper om = new ObjectMapper();
    Map map = om.convertValue(build, Map.class);
//    Map map = om.readValue(om.writeValueAsString(build), Map.class);
    System.out.println(map);
  }

  @Test
  void testGetContent() throws IOException {
    EnumTemplateVars build = EnumTemplateVars.builder().pkg("org.example.demo")
        .cls("HelloType")
        .enumType("HelloType")
        .enumTypeFull("org.example.demo.HelloType").build();
    CodegenContext context = new CodegenContext(build, EnumTemplate.Enum, null);
    new DefaultCodegenEntry().generate(context);
  }


  @Test
  void testExecute() throws IOException {
    EnumTemplateVars build = EnumTemplateVars.builder().pkg("org.example.demo")
        .cls("HelloType")
        .enumType("HelloType")
        .enumTypeFull("org.example.demo.HelloType").build();
    CodegenContext context = new CodegenContext(build, EnumTemplate.Enum, null);
    CodegenExecutor.execute(CodegenEntryType.Default, context);
  }


}
