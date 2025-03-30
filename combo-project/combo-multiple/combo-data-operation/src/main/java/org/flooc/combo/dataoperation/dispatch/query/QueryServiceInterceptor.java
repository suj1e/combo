package org.flooc.combo.dataoperation.dispatch.query;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.lang.NonNull;

/**
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
public class QueryServiceInterceptor implements MethodInterceptor {

  private final Class<?> queryRepoType;
  private final Class<?> voMapperType;

  public QueryServiceInterceptor(Class<?> queryRepoType, Class<?> voMapperType) {
    this.queryRepoType = queryRepoType;
    this.voMapperType = voMapperType;
  }


  @Override
  public Object invoke(@NonNull MethodInvocation invocation) throws Throwable {
    Method method = invocation.getMethod();
    return QueryServiceDispatchUtils.dispatch(new QueryServiceExecutor<>(
            queryRepoType, voMapperType),
        method,
        invocation.getArguments());
  }

}
