package org.flooc.combo.dataoperation.dispatch.cmd;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.flooc.combo.dataoperation.cmd.CreateCmd;
import org.flooc.combo.dataoperation.cmd.UpdateCmd;
import org.flooc.combo.dataoperation.dispatch.ServiceImplDispatchUtils;

/**
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
public final class CmdServiceDispatchUtils {

  private CmdServiceDispatchUtils() {
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public static Object dispatch(CmdServiceExecutor executor, Method method, Object[] args) {
    String methodName = method.getName();
    switch (methodName) {
      case CmdMethods.CREATE -> {
        return executor.create((CreateCmd) args[0]);
      }
      case CmdMethods.UPDATE -> {
        executor.update((UpdateCmd) args[0]);
        return null;
      }
      case CmdMethods.DELETE -> {
        executor.delete((String) args[0]);
        return null;
      }
      case CmdMethods.VALID -> {
        executor.valid((String) args[0]);
        return null;
      }
      case CmdMethods.INVALID -> {
        executor.invalid((String) args[0]);
        return null;
      }
    }
    return ServiceImplDispatchUtils.dispatch(CustomizedCmdService.class, method, args);
  }


  static class CmdMethods {

    public static final String CREATE = "create";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";
    public static final String VALID = "valid";
    public static final String INVALID = "invalid";

  }

}
