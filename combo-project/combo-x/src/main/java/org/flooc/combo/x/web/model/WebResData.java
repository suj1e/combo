package org.flooc.combo.x.web.model;

import lombok.Builder;
import lombok.Getter;
import org.flooc.combo.common.exception.ExceptionEnum;
import org.flooc.combo.x.web.WebCodeEnum;

/**
 * Web响应模型
 *
 * @author sujie
 * @since 1.0.0
 */
@Getter
@Builder
public class WebResData<T> {

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

  public static WebResData<Void> forOK() {
    return WebResData.<Void>builder()
        .code(WebCodeEnum.SUCCESS.getCode())
        .msg(WebCodeEnum.SUCCESS.getText())
        .success(true)
        .build();
  }

  public static <T> WebResData<T> forOK(T data) {
    return WebResData.<T>builder()
        .code(WebCodeEnum.SUCCESS.getCode())
        .msg(WebCodeEnum.SUCCESS.getText())
        .data(data)
        .success(true)
        .build();
  }

  public static WebResData<Void> forFail(WebCodeEnum webCode, String msg) {
    return WebResData.<Void>builder()
        .code(webCode.getCode())
        .msg(msg)
        .success(false)
        .build();
  }

  public static WebResData<Void> forFail(String msg) {
    return forFail(WebCodeEnum.ERROR, msg);
  }

  public static WebResData<Void> forFail(ExceptionEnum<?> exceptionEnum) {
    return forFail(WebCodeEnum.ERROR, exceptionEnum.getText());
  }

}
