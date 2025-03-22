package org.flooc.combo.x.data.common.repo;

import java.util.Optional;
import org.flooc.combo.x.data.common.qry.DataPage;
import org.flooc.combo.x.data.common.qry.DataPageable;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface QryRepo<E, I, Q> {

	Optional<E> qryById(I id);

	boolean exists(I id);

	DataPage<E> qryByPage(Q qry, DataPageable pageable);

}
