package org.flooc.combo.webmvc;

import lombok.Builder;
import lombok.Getter;
import org.flooc.combo.common.constant.CommonBizErrorCode;
import org.flooc.combo.common.constant.ErrorCode;

/**
 * Web响应模型
 *
 * @author sujie
 * @since 1.0.0
 */
@Getter
@Builder
public class WebMvcResData<T> {


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

  public static WebMvcResData<Void> success() {
    return WebMvcResData.<Void>builder()
        .code(SUCCESS_CODE)
        .msg(SUCCESS_MSG)
        .success(true)
        .build();
  }

  public static <T> WebMvcResData<T> success(T data) {
    return WebMvcResData.<T>builder()
        .code(SUCCESS_CODE)
        .msg(SUCCESS_MSG)
        .data(data)
        .success(true)
        .build();
  }

  public static <T> WebMvcResData<T> fail(Integer code, String msg) {
    return WebMvcResData.<T>builder()
        .code(code)
        .msg(msg)
        .success(false)
        .build();
  }

  public static <T> WebMvcResData<T> fail(String msg) {
    return fail(CommonBizErrorCode.AppInternalError.getCode(), msg);
  }

  public static <T> WebMvcResData<T> fail(ErrorCode<?> errorCode) {
    return fail(errorCode.getCode(), errorCode.getText());
  }

}
