package org.flooc.combo.mft.codegen.core;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;
import java.util.concurrent.ConcurrentHashMap;
import org.flooc.combo.mft.codegen.exception.CodegenException;

/**
 * 使用spi机制获取执行类
 *
 * @author sujie
 * @since 1.0.0
 */
public class CodegenExecutor {

  private static final Map<CodegenEntryType, CodegenEntry> CODEGEN_ENTRY_CACHE = new ConcurrentHashMap<>();

  static {
    ServiceLoader<CodegenEntry> sl = ServiceLoader.load(CodegenEntry.class,
        CodegenExecutor.class.getClassLoader());
    sl.stream().filter(Objects::nonNull).map(Provider::get).filter(Objects::nonNull)
        .forEach(i -> CODEGEN_ENTRY_CACHE.put(i.type(), i));
  }

  public static void executeIdeaPlugin(CodegenContext context) throws IOException {
    execute(CodegenEntryType.IdeaPlugin, context);
  }

  public static void execute(CodegenEntryType type, CodegenContext context) throws IOException {
    Preconditions.checkNotNull(type);
    if (CODEGEN_ENTRY_CACHE.isEmpty() || !CODEGEN_ENTRY_CACHE.containsKey(type)) {
      throw new CodegenException("Failed to generate, generator is empty");
    }
    CODEGEN_ENTRY_CACHE.get(type).generate(context);
  }

}
