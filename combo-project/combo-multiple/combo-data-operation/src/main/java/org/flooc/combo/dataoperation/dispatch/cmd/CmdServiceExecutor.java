package org.flooc.combo.dataoperation.dispatch.cmd;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.flooc.combo.common.constant.CommonBizErrorCode;
import org.flooc.combo.common.exception.BizRuntimeException;
import org.flooc.combo.common.util.SpringAppUtil;
import org.flooc.combo.dataoperation.cmd.CreateCmd;
import org.flooc.combo.dataoperation.cmd.UpdateCmd;
import org.flooc.combo.dataoperation.core.EntityOperations;
import org.flooc.combo.dataoperation.mapper.CmdMapper;
import org.flooc.combo.dataoperation.repo.CmdRepo;
import org.flooc.combo.dataoperation.repo.QueryRepo;
import org.flooc.combo.dataoperation.support.AbstractEntity;
import org.flooc.combo.dataoperation.validate.ValidateUtils;
import org.springframework.util.Assert;

/**
 * @param <E> Entity
 * @param <F> CmdRepo
 * @param <H> QueryRepo
 * @param <Q> Query
 * @param <M> CmdMapper
 * @param <C> CreateCmd
 * @param <U> UpdateCmd
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
public class CmdServiceExecutor<E extends AbstractEntity, Q, F extends CmdRepo<E, String>, H extends QueryRepo<E, String, Q>, M extends CmdMapper<E, C, U>, C extends CreateCmd, U extends UpdateCmd> implements
    CmdService<C, U> {

  private final H queryRepo;
  private final F cmdRepo;
  private final M cmdMapper;

  @SuppressWarnings("unchecked")
  public CmdServiceExecutor(Class<?> queryRepoType, Class<?> cmdRepoType, Class<?> cmdMapperType) {
    this.queryRepo = (H) SpringAppUtil.getBean(queryRepoType);
    this.cmdRepo = (F) SpringAppUtil.getBean(cmdRepoType);
    this.cmdMapper = (M) SpringAppUtil.getBean(cmdMapperType);
  }


  @Override
  public String create(C createCmd) {
    Optional<E> entity = EntityOperations.doCreate(cmdRepo).create(() -> {
      Assert.state(createCmd != null, "CreateCmd must not be null");
      ValidateUtils.validate(createCmd);
      return cmdMapper.cmd2Entity(createCmd);
    }).handle(E::init).execute();
    return entity.map(E::getId).orElse(null);
  }

  @Override
  public void update(U updateCmd) {
    EntityOperations.doUpdate(cmdRepo).apply(() -> {
      Assert.state(updateCmd != null, "UpdateCmd must not be null");
      Assert.state(updateCmd.getId() != null, "UpdateCmd id must not be null");
      ValidateUtils.validate(updateCmd);
      E entity = queryRepo.queryById(updateCmd.getId())
          .orElseThrow(() -> new BizRuntimeException(CommonBizErrorCode.NotFindData));
      return cmdMapper.applyUpdate(updateCmd, entity);
    }).execute();
  }

  @Override
  public void delete(String id) {
    EntityOperations.doDelete(cmdRepo).apply(() -> {
      Assert.state(id != null, "Id must not be nul");
      Assert.state(queryRepo.exists(id), "Failed to delete, the data doesn't exist");
      return cmdMapper.buildOfOnly(id);
    }).execute();
  }

  @Override
  public void valid(String id) {
    EntityOperations.doUpdate(cmdRepo).load(() -> {
      Assert.state(id != null, "Id must not be nul");
      return queryRepo.queryById(id)
          .orElseThrow(() -> new BizRuntimeException(CommonBizErrorCode.NotFindData));
    }).handle(E::valid).execute();
  }

  @Override
  public void invalid(String id) {
    EntityOperations.doUpdate(cmdRepo).load(() -> {
      Assert.state(id != null, "Id must not be nul");
      return queryRepo.queryById(id)
          .orElseThrow(() -> new BizRuntimeException(CommonBizErrorCode.NotFindData));
    }).handle(E::invalid).execute();
  }
}
