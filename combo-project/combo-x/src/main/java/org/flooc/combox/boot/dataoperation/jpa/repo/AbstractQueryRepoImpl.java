package org.flooc.combox.boot.dataoperation.jpa.repo;

import com.querydsl.core.BooleanBuilder;
import java.util.Optional;
import org.flooc.combo.dataoperation.query.DataPage;
import org.flooc.combo.dataoperation.query.DataPageable;
import org.flooc.combo.dataoperation.repo.QueryRepo;
import org.flooc.combo.dataoperation.support.Entity;
import org.flooc.combox.boot.dataoperation.jpa.JpaQueryAssembler;
import org.flooc.combox.boot.dataoperation.jpa.mapper.POMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author sujie
 * @since 1.0.0
 */
public abstract class AbstractQueryRepoImpl<P, E extends Entity<String>, J extends JpaRepository<P, String> & QuerydslPredicateExecutor<P>, M extends POMapper<P, E>, Q> implements
    QueryRepo<E, String, Q> {

  @Autowired
  protected J baseJpaQueryRepo;
  @Autowired
  protected M basePOMapper;

  @Override
  public Optional<E> queryById(String id) {
    return baseJpaQueryRepo.findById(id).map(basePOMapper::po2Entity);
  }

  @Override
  public boolean exists(String id) {
    return baseJpaQueryRepo.existsById(id);
  }

  @Override
  public DataPage<E> queryByPage(Q pageQuery,
      DataPageable pageable) {
    Page<P> poPage = baseJpaQueryRepo.findAll(pageQueryCondition(pageQuery),
        JpaQueryAssembler.defaultPageRequest(pageable));
    return JpaQueryAssembler.of(poPage, basePOMapper::po2Entity);
  }

  private BooleanBuilder pageQueryCondition(Q pageQuery) {
    BooleanBuilder bb = new BooleanBuilder();
    Optional.ofNullable(pageQuery).ifPresent(i -> {
      PropertyMapper propertyMapper = PropertyMapper.get();
      buildPageQueryCondition(bb, i, propertyMapper);
    });
    return bb;
  }

  protected abstract void buildPageQueryCondition(BooleanBuilder bb, Q pageQuery,
      PropertyMapper propertyMapper);

}
