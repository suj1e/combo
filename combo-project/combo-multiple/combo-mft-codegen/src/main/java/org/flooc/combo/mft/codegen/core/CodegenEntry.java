package org.flooc.combo.mft.codegen.core;

import java.io.IOException;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface CodegenEntry {

  void generate(CodegenContext context) throws IOException;

  CodegenEntryType type();
}
