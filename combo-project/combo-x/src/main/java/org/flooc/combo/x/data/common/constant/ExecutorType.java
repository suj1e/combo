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
public enum ExecutorType implements BizEnum<ExecutorType> {

	SERIAL(1, "串行"),
	PARALLEL(2, "并行"),

	;

	private final Integer code;

	private final String text;

	ExecutorType(Integer code, String text) {
		this.code = code;
		this.text = text;
	}

	public static Optional<ExecutorType> of(Integer code) {
		return Optional.ofNullable(AppEnum.parse(ExecutorType.class, code));
	}

}
