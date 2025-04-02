package org.flooc.combo.mft.codegen.util;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.security.CodeSource;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author sujie
 * @since 1.0.0
 */
public final class TemplateUtils {

  private TemplateUtils() {
  }

  private static final Map<String, String> TEMPLATE_CACHE = new ConcurrentHashMap<>();


  public static String read(String path) throws IOException {
    if (TEMPLATE_CACHE.containsKey(path)) {
      return TEMPLATE_CACHE.get(path);
    }
    try {
      // 普通读取
      try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path)) {
        Preconditions.checkNotNull(inputStream);
        String templateContent = new String(inputStream.readAllBytes());
        TEMPLATE_CACHE.put(path, templateContent);
        return templateContent;
      }
    } catch (Exception e) {
      // jar方式读取
      CodeSource codeSource = TemplateUtils.class.getProtectionDomain().getCodeSource();
      String jarPath = codeSource.getLocation().getPath();
      try (JarFile jarFile = new JarFile(jarPath)) {
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
          JarEntry jarEntry = entries.nextElement();
          if (jarEntry.getName().endsWith(path)) {
            try (InputStream inputStream = jarFile.getInputStream(jarEntry);) {
              String templateContent = new String(inputStream.readAllBytes());
              TEMPLATE_CACHE.put(path,templateContent);
              return templateContent;
            }
          }
        }
      }
    }
    return null;
  }

}
