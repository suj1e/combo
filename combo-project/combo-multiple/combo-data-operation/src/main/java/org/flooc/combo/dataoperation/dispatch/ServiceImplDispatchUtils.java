package org.flooc.combo.dataoperation.dispatch;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;
import org.flooc.combo.common.constant.CommonBizErrorCode;
import org.flooc.combo.common.exception.BizRuntimeException;
import org.flooc.combo.common.util.SpringAppUtil;
import org.springframework.core.annotation.AnnotatedElementUtils;

/**
 * @author sujie
 * @since 1.0.0
 */
public final class ServiceImplDispatchUtils {

  private ServiceImplDispatchUtils() {
  }


  @SuppressWarnings("unchecked")
  public static Object dispatch(Class<? extends Annotation> customServiceType, Method method,
      Object[] args) {
    if (AnnotatedElementUtils.hasAnnotation(method.getDeclaringClass(),
        customServiceType)) {
      try {
        Map<String, Object> serviceImplBeansMap = (Map<String, Object>) SpringAppUtil.getBeansOfType(
            method.getDeclaringClass());
        Object serviceImplBean = serviceImplBeansMap.entrySet()
            .stream().filter(entry -> entry.getKey().endsWith("Impl"))
            .map(Entry::getValue).findFirst().orElse(null);
        if (serviceImplBean == null) {
          throw new BizRuntimeException(CommonBizErrorCode.DispatchExecuteError);
        }
        return method.invoke(serviceImplBean, args);
      } catch (Exception e) {
        throw new BizRuntimeException(CommonBizErrorCode.DispatchExecuteError, e);
      }
    }
    return null;
  }
}
