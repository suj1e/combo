package org.flooc.combox.boot.dataoperation.jpa.repo;

import org.flooc.combo.dataoperation.repo.CmdRepo;
import org.flooc.combo.dataoperation.support.Entity;
import org.flooc.combox.boot.dataoperation.jpa.mapper.POMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sujie
 * @since 1.0.0
 */
public abstract class AbstractCmdRepoImpl<P, E extends Entity<String>, J extends JpaRepository<P, String>, M extends POMapper<P, E>> implements
    CmdRepo<E, String> {

  @Autowired
  protected J baseJpaCmdRepo;

  @Autowired
  protected M basePOMapper;

  @Override
  public E createEntity(E entity) {
    P po = baseJpaCmdRepo.save(
        basePOMapper.entity2PO(entity));
    return basePOMapper.po2Entity(po);
  }

  @Override
  public E updateEntity(E entity) {
    P po = baseJpaCmdRepo.saveAndFlush(
        basePOMapper.entity2PO(entity));
    return basePOMapper.po2Entity(po);
  }

  @Override
  public void deleteEntity(E entity) {
    baseJpaCmdRepo.deleteById(entity.getId());
  }
}
