package org.flooc.combo.dataoperation.support;

import java.util.Optional;
import org.flooc.combo.dataoperation.enums.DataState;
import org.flooc.combo.dataoperation.mapper.DataMapper;

/**
 * @author sujie
 * @since 1.0.0
 */
public class DataStateMapper implements DataMapper<DataState, Integer> {

	@Override
	public DataState val2Obj(Integer code) {
		return DataState.of(code).orElse(null);
	}

	@Override
	public Integer obj2Val(DataState dataState) {
		return Optional.ofNullable(dataState).map(DataState::getCode).orElse(null);
	}

}
