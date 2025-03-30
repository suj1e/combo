package org.flooc.combo.dataoperation.dispatch.query;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.flooc.combo.common.util.SpringAppUtil;
import org.flooc.combo.dataoperation.mapper.VOMapper;
import org.flooc.combo.dataoperation.query.DataPage;
import org.flooc.combo.dataoperation.query.DataPageable;
import org.flooc.combo.dataoperation.query.PageRequestWrapper;
import org.flooc.combo.dataoperation.repo.QueryRepo;
import org.flooc.combo.dataoperation.support.Entity;

/**
 * @param <N> VOMapper
 * @param <V> VO
 * @param <E> Entity
 * @param <H> QueryRepo
 * @param <Q> Query
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
public class QueryServiceExecutor<N extends VOMapper<V, E>, E extends Entity<String>, H extends QueryRepo<E, String, Q>, V, Q> implements
    QueryService<V, Q> {

  private final H queryRepo;
  private final N voMapper;

  @SuppressWarnings("unchecked")
  public QueryServiceExecutor(Class<?> queryRepoType, Class<?> voMapperType) {
    this.queryRepo = (H) SpringAppUtil.getBean(queryRepoType);
    this.voMapper = (N) SpringAppUtil.getBean(voMapperType);
  }


  @Override
  public DataPage<V> findByPage(PageRequestWrapper<Q> wrapper) {
    DataPage<E> entityPage = queryRepo.queryByPage(wrapper.getRequestData(),
        DataPageable.of(wrapper));
    return DataPage.of(entityPage, voMapper::entity2VO);
  }

  @Override
  public V findById(String id) {
    Optional<E> entity = queryRepo.queryById(id);
    return entity.map(voMapper::entity2VO).orElse(null);
  }


}
