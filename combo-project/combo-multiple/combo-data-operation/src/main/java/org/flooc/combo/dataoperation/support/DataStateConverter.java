package org.flooc.combo.dataoperation.support;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Optional;
import org.flooc.combo.dataoperation.enums.DataState;

@Converter
public class DataStateConverter implements AttributeConverter<DataState, Integer> {

	@Override
	public Integer convertToDatabaseColumn(DataState attribute) {
		return Optional.ofNullable(attribute).map(DataState::getCode).orElse(null);
	}

	@Override
	public DataState convertToEntityAttribute(Integer dbData) {
		return DataState.of(dbData).orElse(null);
	}

}
