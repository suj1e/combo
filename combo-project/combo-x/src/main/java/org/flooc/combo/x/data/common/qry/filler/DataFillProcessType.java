package org.flooc.combo.x.data.common.qry.filler;

import java.util.Optional;
import lombok.Getter;
import org.flooc.combo.common.constant.AppEnum;
import org.flooc.combo.common.constant.BizEnum;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
public enum DataFillProcessType implements BizEnum<DataFillProcessType> {

	CONCURRENT(2, "并行"), ORDERLY(1, "串行"),

	;

	private final Integer code;

	private final String text;

	DataFillProcessType(Integer code, String text) {
		this.code = code;
		this.text = text;
	}

	public static Optional<DataFillProcessType> of(Integer code) {
		return Optional.ofNullable(AppEnum.parse(DataFillProcessType.class, code));
	}

}
