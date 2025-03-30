package org.flooc.combo.dataoperation.dispatch.query;

import org.flooc.combo.dataoperation.query.DataPage;
import org.flooc.combo.dataoperation.query.PageRequestWrapper;

/**
 * @param <Q> Query
 * @param <V> VO
 * @author sujie
 * @since 1.0.0
 */
@NoQueryService
public interface QueryService<V, Q> {

  DataPage<V> findByPage(PageRequestWrapper<Q> wrapper);

  V findById(String id);

}
