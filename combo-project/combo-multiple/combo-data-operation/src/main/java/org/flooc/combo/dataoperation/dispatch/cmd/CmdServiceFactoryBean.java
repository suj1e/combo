package org.flooc.combo.dataoperation.dispatch.cmd;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author sujie
 * @since 1.0.0
 */
@SuppressWarnings("rawtypes")
@RequiredArgsConstructor
public class CmdServiceFactoryBean<S extends CmdService> implements FactoryBean<S> {

  private final Class<?> serviceInterface;
  @Setter
  private Class<?> queryRepoType;
  @Setter
  private Class<?> cmdRepoType;
  @Setter
  private Class<?> cmdMapperType;


  @SuppressWarnings("unchecked")
  @Override
  public S getObject() throws Exception {
    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.addInterface(serviceInterface);
    proxyFactory.addAdvice(
        new CmdServiceInterceptor(queryRepoType, cmdRepoType, cmdMapperType));
    return (S) proxyFactory.getProxy();
  }

  @Override
  public Class<?> getObjectType() {
    return serviceInterface;
  }

}
