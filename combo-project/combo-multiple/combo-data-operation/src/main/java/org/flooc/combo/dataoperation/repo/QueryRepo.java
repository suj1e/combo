package org.flooc.combo.dataoperation.repo;

import java.util.Optional;
import org.flooc.combo.dataoperation.query.DataPage;
import org.flooc.combo.dataoperation.query.DataPageable;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface QueryRepo<E, I, Q> {

	Optional<E> queryById(I id);

	boolean exists(I id);

	DataPage<E> queryByPage(Q query, DataPageable pageable);

}
