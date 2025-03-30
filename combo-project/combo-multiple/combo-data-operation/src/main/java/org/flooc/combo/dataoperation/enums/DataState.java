package org.flooc.combo.dataoperation.enums;

import java.util.Optional;
import lombok.Getter;
import org.flooc.combo.common.constant.AppCode;
import org.flooc.combo.common.constant.BizCode;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
public enum DataState implements BizCode<DataState> {

	VALID(1, "有效"), INVALID(0, "失效"),

	;

	private final Integer code;

	private final String text;

	DataState(Integer code, String text) {
		this.code = code;
		this.text = text;
	}

	public static Optional<DataState> of(Integer code) {
		return Optional.ofNullable(AppCode.parse(DataState.class, code));
	}

}
