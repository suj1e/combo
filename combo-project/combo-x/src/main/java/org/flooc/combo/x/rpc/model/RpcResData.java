package org.flooc.combo.x.rpc.model;

import lombok.Builder;
import lombok.Getter;
import org.flooc.combo.common.exception.ExceptionEnum;
import org.flooc.combo.x.rpc.RpcCodeEnum;

/**
 * RPC响应模型
 *
 * @author sujie
 * @since 1.0.0
 */
@Getter
@Builder
public class RpcResData<T> {

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

  public static RpcResData<Void> forOK() {
    return RpcResData.<Void>builder()
        .code(RpcCodeEnum.SUCCESS.getCode())
        .msg(RpcCodeEnum.SUCCESS.getText())
        .success(true)
        .build();
  }

  public static <T> RpcResData<T> forOK(T data) {
    return RpcResData.<T>builder()
        .code(RpcCodeEnum.SUCCESS.getCode())
        .msg(RpcCodeEnum.SUCCESS.getText())
        .data(data)
        .success(true)
        .build();
  }

  public static RpcResData<Void> forFail(RpcCodeEnum rpcCode, String msg) {
    return RpcResData.<Void>builder()
        .code(rpcCode.getCode())
        .msg(msg)
        .success(false)
        .build();
  }

  public static RpcResData<Void> forFail(String msg) {
    return forFail(RpcCodeEnum.ERROR, msg);
  }

  public static RpcResData<Void> forFail(ExceptionEnum<?> exceptionEnum) {
    return forFail(RpcCodeEnum.ERROR, exceptionEnum.getText());
  }


}
