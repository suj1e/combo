package org.flooc.combo.x.data.common.constant;

import java.util.Optional;
import lombok.Getter;
import org.flooc.combo.common.constant.AppEnum;
import org.flooc.combo.common.constant.BizEnum;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
public enum ValidStatus implements BizEnum<ValidStatus> {

	VALID(1, "有效"), INVALID(0, "失效"),

	;

	private final Integer code;

	private final String text;

	ValidStatus(Integer code, String text) {
		this.code = code;
		this.text = text;
	}

	public static Optional<ValidStatus> of(Integer code) {
		return Optional.ofNullable(AppEnum.parse(ValidStatus.class, code));
	}

}
