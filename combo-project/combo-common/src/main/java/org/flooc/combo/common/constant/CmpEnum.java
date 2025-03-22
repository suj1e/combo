package org.flooc.combo.common.constant;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface CmpEnum<T extends Enum<T> & CmpEnum<T>> extends AppEnum<T> {

}
