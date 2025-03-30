package org.flooc.combo.dataoperation.query.filler.support;

import java.util.Optional;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
public class DataElReader<T, R> implements Function<T, R> {

	private final Expression expression;

	private final EvaluationContext evaluationContext;

	public DataElReader(String expressionStr, ExpressionParser expressionParser, ParserContext parserContext,
			BeanResolver beanResolver) {
		this.expression = expressionParser.parseExpression(expressionStr, parserContext);
		StandardEvaluationContext context = new StandardEvaluationContext();
		context.setBeanResolver(beanResolver);
		this.evaluationContext = context;
	}

	@SuppressWarnings("unchecked")
	@Override
	public R apply(T o) {
		return (R) Optional.ofNullable(o).map(i -> expression.getValue(evaluationContext, o)).orElse(null);
	}

}
