package org.flooc.combox.boot.web.logging;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sujie
 * @since 1.0.0
 */
public class AppLoggingConfiguration implements EnvironmentAware {

  private final String[] scans;

  private Environment environment;

  public AppLoggingConfiguration(String[] scans) {
    this.scans = scans;
  }


  @Bean
  @Role(value = BeanDefinition.ROLE_INFRASTRUCTURE)
  public DefaultPointcutAdvisor appLoggingAdvisor() {
    DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
    advisor.setAdvice(buildInterceptor());
    advisor.setPointcut(buildPointcut());
    return advisor;
  }

  private Pointcut buildPointcut() {
    ComposablePointcut annotationPointcut = new ComposablePointcut();
    annotationPointcut.union(new AnnotationMatchingPointcut(Service.class, true))
        .union(new AnnotationMatchingPointcut(Repository.class))
        .union(new AnnotationMatchingPointcut(RestController.class, true));
    return annotationPointcut.intersection(new AppLoggingPointcut(scans));
  }

  private Advice buildInterceptor() {
    return new AppLoggingInterceptor(environment);
  }

  @Override
  public void setEnvironment(@NonNull Environment environment) {
    Assert.notNull(environment, "App environment must not be null");
    this.environment = environment;
  }

  static class AppLoggingInterceptor implements MethodInterceptor {

    private final Environment environment;

    AppLoggingInterceptor(Environment environment) {
      this.environment = environment;
    }

    @Nullable
    @Override
    public Object invoke(@NonNull MethodInvocation invocation) throws Throwable {
      Object invoker = invocation.getThis();
      Method method = invocation.getMethod();
      Object[] arguments = invocation.getArguments();
      String methodName = method.getName();
      Class<?> invokerType = Objects.requireNonNullElse(invoker, this).getClass();
      boolean devEnv = environment.acceptsProfiles(Profiles.of("dev", "cloud-dev"));
      Logger log = LoggerFactory.getLogger(invokerType);
      if (log.isDebugEnabled()) {
        if (arguments.length == 0) {
          log.debug("Enter: {}(), no args", methodName);
        } else {
          log.debug("Enter: {}(), args[s] = {}", methodName, arguments);
        }
      }
      try {
        Object result = invocation.proceed();
        if (log.isDebugEnabled()) {
          Class<?> methodReturnType = invocation.getMethod().getReturnType();
          String methodReturnTypeSimpleName = methodReturnType.getSimpleName();
          if ("void".equalsIgnoreCase(methodReturnTypeSimpleName)) {
            log.debug("Exit: {}(), resultType is void", methodName);
          } else if (devEnv) {
            log.debug("Exit: {}(), result = {}", methodName, result);
          } else {
            log.debug("Exit: {}()", methodName);
          }
        }
        return result;
      } catch (IllegalArgumentException e) {
        log.error("Illegal args, {}() with args[s] = {}", arguments);
        throw e;
      } catch (Exception ex) {
        String msg = String.format("Exception in %s(), cause = '%s'", methodName,
            ex.getCause() != null ? String.valueOf(ex.getCause()) : "NULL");
        if (devEnv) {
          msg = msg + String.format(", exception = '%s'", ex);
        }
        log.error(msg);
        throw ex;
      }
    }

  }

  static class AppLoggingPointcut implements MethodMatcher {

    private final String[] scans;

    AppLoggingPointcut(String[] scans) {
      this.scans = scans;
    }

    @Override
    public boolean matches(@NonNull Method method, @NonNull Class<?> targetClass) {
      AppLogging appLogging = AnnotationUtils.findAnnotation(targetClass, AppLogging.class);
      if (Objects.nonNull(appLogging)) {
        return appLogging.enable();
      }
      return Arrays.stream(scans).anyMatch(scan -> targetClass.getName().startsWith(scan));
    }

    @Override
    public boolean isRuntime() {
      return true;
    }

    @Override
    public boolean matches(@NonNull Method method, @NonNull Class<?> targetClass,
        @Nullable Object... args) {
      AppLogging appLogging = AnnotationUtils.findAnnotation(targetClass, AppLogging.class);
      if (Objects.nonNull(appLogging)) {
        return appLogging.enable();
      }
      return Arrays.stream(scans).anyMatch(scan -> targetClass.getName().startsWith(scan));
    }

  }

}
