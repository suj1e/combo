package org.flooc.combo.common.enums;

import java.util.Optional;
import lombok.Getter;
import org.flooc.combo.common.constant.AppCode;
import org.flooc.combo.common.constant.BizCode;
import org.flooc.combo.common.constant.CmpCode;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
public enum ExecutorType implements BizCode<ExecutorType> {

	ORDERLY(1, "串行"),
	CONCURRENT(2, "并发"),
	;

	private final Integer code;

	private final String text;

	ExecutorType(Integer code, String text) {
		this.code = code;
		this.text = text;
	}

	public static Optional<ExecutorType> of(Integer code) {
		return Optional.ofNullable(AppCode.parse(ExecutorType.class, code));
	}

}
