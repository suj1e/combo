package org.flooc.combo.common.constant;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface BizEnum<T extends Enum<T> & BizEnum<T>> extends AppEnum<T> {

}
