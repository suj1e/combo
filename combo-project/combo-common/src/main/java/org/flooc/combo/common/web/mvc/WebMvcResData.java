package org.flooc.combo.common.web.mvc;

import lombok.Builder;
import lombok.Getter;
import org.flooc.combo.common.exception.ExceptionEnum;

/**
 * Web响应模型
 *
 * @author sujie
 * @since 1.0.0
 */
@Getter
@Builder
public class WebMvcResData<T> {

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

  public static WebMvcResData<Void> forOK() {
    return WebMvcResData.<Void>builder()
        .code(WebMvcCodeEnum.SUCCESS.getCode())
        .msg(WebMvcCodeEnum.SUCCESS.getText())
        .success(true)
        .build();
  }

  public static <T> WebMvcResData<T> forOK(T data) {
    return WebMvcResData.<T>builder()
        .code(WebMvcCodeEnum.SUCCESS.getCode())
        .msg(WebMvcCodeEnum.SUCCESS.getText())
        .data(data)
        .success(true)
        .build();
  }

  public static WebMvcResData<Void> forFail(WebMvcCodeEnum webCode, String msg) {
    return WebMvcResData.<Void>builder()
        .code(webCode.getCode())
        .msg(msg)
        .success(false)
        .build();
  }

  public static WebMvcResData<Void> forFail(String msg) {
    return forFail(WebMvcCodeEnum.ERROR, msg);
  }

  public static WebMvcResData<Void> forFail(ExceptionEnum<?> exceptionEnum) {
    return forFail(WebMvcCodeEnum.ERROR, exceptionEnum.getText());
  }

}
