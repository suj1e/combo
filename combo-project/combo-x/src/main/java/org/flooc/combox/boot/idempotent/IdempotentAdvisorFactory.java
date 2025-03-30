package org.flooc.combox.boot.idempotent;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * @author sujie
 * @since 1.0.0
 */
public final class IdempotentAdvisorFactory {

	private IdempotentAdvisorFactory() {
	}

	public static DefaultPointcutAdvisor buildAdvisor() {
		return new DefaultPointcutAdvisor(new AnnotationMatchingPointcut(null, Idempotent.class),
				new IdempotentInterceptor());
	}

	static class IdempotentInterceptor implements MethodInterceptor {

		@Override
		public @Nullable Object invoke(@NonNull MethodInvocation invocation) throws Throwable {
			// TODO 幂等拦截实现
			return null;
		}

	}

}
