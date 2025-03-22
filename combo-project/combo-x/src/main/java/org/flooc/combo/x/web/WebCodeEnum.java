package org.flooc.combo.x.web;

import java.util.Optional;
import lombok.Getter;
import org.flooc.combo.common.constant.AppEnum;
import org.flooc.combo.common.constant.BizEnum;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
public enum WebCodeEnum implements BizEnum<WebCodeEnum> {

	SUCCESS(200, "成功"), ERROR(500, "错误"),;

	private final Integer code;

	private final String text;

	WebCodeEnum(Integer code, String text) {
		this.code = code;
		this.text = text;
	}


	public static Optional<WebCodeEnum> of(Integer code) {
		return Optional.ofNullable(AppEnum.parse(WebCodeEnum.class, code));
	}

}
