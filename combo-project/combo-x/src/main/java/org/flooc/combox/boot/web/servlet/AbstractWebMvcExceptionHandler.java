package org.flooc.combox.boot.web.servlet;

import org.flooc.combo.common.exception.BizRuntimeException;
import org.flooc.combo.common.exception.CmpRuntimeException;
import org.flooc.combo.web.util.ExceptionUtils;
import org.flooc.combo.webmvc.WebMvcResData;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 注意先后捕获顺序
 *
 * @author sujie
 * @since 1.0.0
 */
public abstract class AbstractWebMvcExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public WebMvcResData<Void> handleArgsException(MethodArgumentNotValidException e) {
    return WebMvcResData.fail(ExceptionUtils.extract(e));
  }

  @ExceptionHandler(IllegalStateException.class)
  public WebMvcResData<Void> handleException(IllegalStateException e) {
    return WebMvcResData.fail(e.getMessage());
  }

  @ExceptionHandler(BizRuntimeException.class)
  public WebMvcResData<Void> handleException(BizRuntimeException e) {
    return WebMvcResData.fail(e.getErrorCode());
  }

  @ExceptionHandler(CmpRuntimeException.class)
  public WebMvcResData<Void> handleException(CmpRuntimeException e) {
    return WebMvcResData.fail(e.getErrorCode());
  }

  @ExceptionHandler(Exception.class)
  public WebMvcResData<Void> handleException(Exception e) {
    return WebMvcResData.fail(e.getMessage());
  }

}
