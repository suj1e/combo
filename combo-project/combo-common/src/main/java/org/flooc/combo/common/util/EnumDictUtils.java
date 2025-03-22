package org.flooc.combo.common.util;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import org.flooc.combo.common.constant.AppEnum;
import org.flooc.combo.common.model.EnumVO;

/**
 * @author sujie
 * @since 1.0.0
 */
public final class EnumDictUtils {

	private EnumDictUtils() {
	}

	public static <T extends Enum<T> & AppEnum<T>> List<EnumVO> getEnumDictVOList(Class<T> clazz) {
		return EnumSet.allOf(clazz).stream().map(i -> EnumVO.builder()
        .code(i.getCode())
        .name(i.name())
        .text(i.getText())
        .build()).collect(Collectors.toList());
	}

}
