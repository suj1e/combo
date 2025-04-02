package org.flooc.combo.mft.codegen.core;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用spi机制获取执行类
 *
 * @author sujie
 * @since 1.0.0
 */
public class CodegenExecutor {

  private static final Map<CodegenEntryType, CodegenEntry> CODEGEN_ENTRY_CACHE = new ConcurrentHashMap<>();

  public static void execute(CodegenEntryType type, CodegenContext context) throws IOException {
    Preconditions.checkNotNull(type);
    if (CODEGEN_ENTRY_CACHE.containsKey(type)) {
      CODEGEN_ENTRY_CACHE.get(type).generate(context);
      return;
    }
    ServiceLoader<CodegenEntry> sl = ServiceLoader.load(CodegenEntry.class);
    if (CODEGEN_ENTRY_CACHE.isEmpty()) {
      sl.stream().filter(Objects::nonNull).map(Provider::get).filter(Objects::nonNull)
          .forEach(i -> CODEGEN_ENTRY_CACHE.put(i.type(), i));
    }
    Optional<CodegenEntry> entryOptional = sl.stream().filter(Objects::nonNull).map(Provider::get)
        .filter(Objects::nonNull)
        .filter(i -> type.equals(i.type()))
        .findFirst();
    if (entryOptional.isPresent()) {
      CodegenEntry codegenEntry = entryOptional.get();
      CODEGEN_ENTRY_CACHE.put(type, codegenEntry);
      codegenEntry.generate(context);
    }
  }

}
