package org.flooc.combox.boot.lazyload;

import java.util.Objects;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.Pointcuts;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * @author sujie
 * @since 1.0.0
 */
public final class LazyLoadProxyFactory {

	private LazyLoadProxyFactory() {
	}

	@SuppressWarnings("unchecked")
	public static <T> T create(T target) {
		ProxyFactory proxyFactory = new ProxyFactory(target);
		proxyFactory.addAdvice(new LazyLoadMethodInterceptor());
		return (T) proxyFactory.getProxy();
	}

	static class LazyLoadMethodInterceptor implements MethodInterceptor {

		@Override
		public @Nullable Object invoke(@NonNull MethodInvocation invocation) throws Throwable {
			Object aThis = invocation.getThis();
			if (Objects.isNull(aThis)) {
				return null;
			}
			if (Pointcuts.GETTERS.getMethodMatcher().matches(invocation.getMethod(), aThis.getClass())) {
				// TODO 解析spel，获取值返回
			}
			return null;
		}

	}

}
