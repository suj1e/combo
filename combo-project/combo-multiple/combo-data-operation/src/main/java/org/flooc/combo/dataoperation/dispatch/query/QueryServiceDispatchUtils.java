package org.flooc.combo.dataoperation.dispatch.query;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.flooc.combo.dataoperation.query.PageRequestWrapper;
import org.flooc.combo.dataoperation.dispatch.ServiceImplDispatchUtils;

/**
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
public final class QueryServiceDispatchUtils {

  private QueryServiceDispatchUtils() {
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public static Object dispatch(QueryServiceExecutor executor, Method method, Object[] args) {
    String methodName = method.getName();
    switch (methodName){
      case QueryMethods.FIND_BY_PAGE -> {
        return executor.findByPage((PageRequestWrapper<?>) args[0]);
      }
      case QueryMethods.FIND_BY_ID -> {
        return executor.findById((String) args[0]);
      }
    }
    return ServiceImplDispatchUtils.dispatch(CustomizedQueryService.class, method, args);
  }


  static class QueryMethods {

    public static final String FIND_BY_PAGE = "findByPage";
    public static final String FIND_BY_ID = "findById";
  }

}
