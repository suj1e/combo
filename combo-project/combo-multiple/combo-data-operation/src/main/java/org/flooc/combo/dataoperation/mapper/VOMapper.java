package org.flooc.combo.dataoperation.mapper;

/**
 * @param <E> Entity
 * @param <V> VO
 * @author sujie
 * @since 1.0.0
 */
public interface VOMapper<V, E> {


  V entity2VO(E entity);


}
