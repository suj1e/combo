package org.flooc.combo.mft.codegen.core;

import java.util.Map;

public class DefaultCodegenEntry extends AbstractCodegenEntryTemplate {

    @Override
    public void doGenerate(String content, Map<ExtMapKey,Object> extMap) {
      System.out.println("--------");
      System.out.println(content);
      System.out.println("--------");
    }

  @Override
  public CodegenEntryType type() {
    return CodegenEntryType.Default;
  }
}