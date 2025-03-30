package org.flooc.combo.dataoperation.core;

import org.flooc.combo.dataoperation.repo.CmdRepo;

/**
 * @author sujie
 * @since 1.0.0
 */
public final class EntityOperations {

  private EntityOperations() {
  }

  public static <T, I> EntityCreator<T, I> doCreate(CmdRepo<T, I> cmdRepo) {
    return new EntityCreator<>(cmdRepo);
  }

  public static <T, I> EntityUpdater<T, I> doUpdate(CmdRepo<T, I> cmdRepo) {
    return new EntityUpdater<>(cmdRepo);
  }

  public static <T, I> EntityDeleter<T, I> doDelete(CmdRepo<T, I> cmdRepo) {
    return new EntityDeleter<>(cmdRepo);
  }

}
