package org.flooc.combo.dataoperation.mapper;

import org.flooc.combo.dataoperation.cmd.CreateCmd;
import org.flooc.combo.dataoperation.cmd.UpdateCmd;
import org.mapstruct.MappingTarget;

/**
 * @param <E> Entity
 * @param <C> CreateCmd
 * @param <U> UpdateCmd
 * @author sujie
 * @since 1.0.0
 */
public interface CmdMapper<E, C extends CreateCmd, U extends UpdateCmd> {

  E cmd2Entity(C createCmd);

  E applyUpdate(U updateCmd, @MappingTarget E entity);

  E buildOfOnly(String id);

}