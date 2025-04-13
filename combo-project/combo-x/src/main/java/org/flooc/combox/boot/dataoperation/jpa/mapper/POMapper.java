package org.flooc.combox.boot.dataoperation.jpa.mapper;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface POMapper<P, E> {

  P entity2PO(E entity);

  E po2Entity(P po);

}
