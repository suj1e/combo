package org.flooc.combo.dataoperation.dispatch.query;

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
public class QueryServiceFactoryBean<S extends QueryService> implements FactoryBean<S> {

  private final Class<?> serviceInterface;
  @Setter
  private Class<?> queryRepoType;
  @Setter
  private Class<?> voMapperType;


  @SuppressWarnings("unchecked")
  @Override
  public S getObject() throws Exception {
    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.addInterface(serviceInterface);
    proxyFactory.addAdvice(
        new QueryServiceInterceptor(queryRepoType, voMapperType));
    return (S) proxyFactory.getProxy();
  }

  @Override
  public Class<?> getObjectType() {
    return serviceInterface;
  }

}
