package org.flooc.combo.x.data.jpa;

import java.util.Optional;
import org.flooc.combo.common.convert.DataMapper;
import org.flooc.combo.x.data.common.constant.ValidStatus;

/**
 * @author sujie
 * @since 1.0.0
 */
public class ValidStatusMapper implements DataMapper<ValidStatus, Integer> {

	@Override
	public ValidStatus val2Obj(Integer code) {
		return ValidStatus.of(code).orElse(null);
	}

	@Override
	public Integer obj2Val(ValidStatus validStatus) {
		return Optional.ofNullable(validStatus).map(ValidStatus::getCode).orElse(null);
	}

}
