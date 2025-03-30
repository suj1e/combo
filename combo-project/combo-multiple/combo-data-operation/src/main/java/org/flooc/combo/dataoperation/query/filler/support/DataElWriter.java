package org.flooc.combo.dataoperation.query.filler.support;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
public class DataElWriter implements BiConsumer<Object, List<Object>> {

	private final String fieldName;

	private final boolean isCollection;

	private final Expression expression;

	public DataElWriter(Field field, ExpressionParser expressionParser) {
		this.fieldName = field.getName();
		this.isCollection = field.getType().isAssignableFrom(Collection.class);
		this.expression = expressionParser.parseExpression(fieldName);
	}

	@Override
	public void accept(Object o, List<Object> objects) {
		if (CollectionUtils.isEmpty(objects)) {
			log.warn("DataFill set value is empty, field:{}", fieldName);
			return;
		}
		if (isCollection) {
			expression.setValue(o, objects);
		}
		else {
			Assert.isTrue(objects.size() == 1,
					String.format("DataFill set value size is not equals 1, field:%s", fieldName));
			expression.setValue(o, objects.getFirst());
		}
	}

}
