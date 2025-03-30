package org.flooc.combo.dataoperation.dispatch.cmd;

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
public class CmdServiceInterceptor implements MethodInterceptor {

  private final Class<?> queryRepoType;
  private final Class<?> cmdRepoType;
  private final Class<?> cmdMapperType;

  public CmdServiceInterceptor(Class<?> queryRepoType, Class<?> cmdRepoType, Class<?> cmdMapperType) {
    this.queryRepoType = queryRepoType;
    this.cmdRepoType = cmdRepoType;
    this.cmdMapperType = cmdMapperType;
  }


  @Override
  public Object invoke(@NonNull MethodInvocation invocation) throws Throwable {
    Method method = invocation.getMethod();
    return CmdServiceDispatchUtils.dispatch(
        new CmdServiceExecutor<>(queryRepoType, cmdRepoType, cmdMapperType), method,
        invocation.getArguments());
  }
}
