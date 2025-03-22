package org.flooc.combo.x.data.jpa;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.flooc.combo.x.data.common.constant.ValidStatus;

@Converter
public class ValidStatusConverter implements AttributeConverter<ValidStatus, Integer> {

	@Override
	public Integer convertToDatabaseColumn(ValidStatus attribute) {
		return Optional.ofNullable(attribute).map(ValidStatus::getCode).orElse(null);
	}

	@Override
	public ValidStatus convertToEntityAttribute(Integer dbData) {
		return ValidStatus.of(dbData).orElse(null);
	}

}
