package org.flooc.combo.common.translate;

/**
 * 全局应用码翻译
 * <p>
 *   1.从配置的应用码映射表中取
 *   2.启动时刷新应用中所有枚举进入redis缓存
 * </p>
 * @author sujie
 * @since 1.0.0
 */
@FunctionalInterface
public interface AppCodeTranslator {

  String tranlsate(String code);

}
