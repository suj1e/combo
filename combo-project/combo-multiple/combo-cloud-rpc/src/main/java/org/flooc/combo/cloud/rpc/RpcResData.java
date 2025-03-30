package org.flooc.combo.cloud.rpc;

import lombok.Builder;
import lombok.Getter;
import org.flooc.combo.common.constant.CommonBizErrorCode;
import org.flooc.combo.common.constant.ErrorCode;

/**
 * RPC响应模型
 *
 * @author sujie
 * @since 1.0.0
 */
@Getter
@Builder
public class RpcResData<T> {

  private final static int SUCCESS_CODE = 200;
  private final static String SUCCESS_MSG = "成功";


  /**
   * 状态码
   */
  private int code;

  /**
   * 信息，一般是错误信息
   */
  private String msg;

  /**
   * 数据
   */
  private T data;

  private boolean success;

  private String traceId;

  public static RpcResData<Void> success() {
    return RpcResData.<Void>builder()
        .code(SUCCESS_CODE)
        .msg(SUCCESS_MSG)
        .success(true)
        .build();
  }

  public static <T> RpcResData<T> success(T data) {
    return RpcResData.<T>builder()
        .code(SUCCESS_CODE)
        .msg(SUCCESS_MSG)
        .data(data)
        .success(true)
        .build();
  }

  public static <T> RpcResData<T> fail(Integer code, String msg) {
    return RpcResData.<T>builder()
        .code(code)
        .msg(msg)
        .success(false)
        .build();
  }

  public static <T> RpcResData<T> fail(String msg) {
    return fail(CommonBizErrorCode.RpcError.getCode(), msg);
  }

  public static <T> RpcResData<T> fail(ErrorCode<?> errorCode) {
    return fail(errorCode.getCode(), errorCode.getText());
  }


}
