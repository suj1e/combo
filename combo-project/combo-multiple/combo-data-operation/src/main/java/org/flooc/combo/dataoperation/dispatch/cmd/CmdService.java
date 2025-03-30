package org.flooc.combo.dataoperation.dispatch.cmd;

import org.flooc.combo.dataoperation.cmd.CreateCmd;
import org.flooc.combo.dataoperation.cmd.UpdateCmd;

/**
 * @param <C> CreateCmd
 * @param <U> UpdateCmd
 * @author sujie
 * @since 1.0.0
 */
@NoCmdService
public interface CmdService<C extends CreateCmd, U extends UpdateCmd> {

  String create(C createCmd);

  void update(U updateCmd);

  void delete(String id);

  void valid(String id);

  void invalid(String id);

}
