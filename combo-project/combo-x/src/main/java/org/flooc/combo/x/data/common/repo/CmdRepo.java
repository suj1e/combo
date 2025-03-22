package org.flooc.combo.x.data.common.repo;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface CmdRepo<E, I> {

	E createE(E entity);

	E updateE(E entity);

	void deleteE(E entity);

}
