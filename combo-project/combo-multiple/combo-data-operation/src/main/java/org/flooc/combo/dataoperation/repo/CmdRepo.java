package org.flooc.combo.dataoperation.repo;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface CmdRepo<E, I> {

	E createEntity(E entity);

	E updateEntity(E entity);

	void deleteEntity(E entity);

}
