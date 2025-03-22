package org.flooc.combo.common.exception;

import java.util.Optional;
import lombok.Getter;
import org.flooc.combo.common.constant.AppEnum;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
public enum CommonBizExceptionEnum implements BizExceptionEnum<CommonBizExceptionEnum> {

  AppError(100000,"系统异常"),
  BizError(100001,"业务异常"),
  CreateError(100002,"新增失败"),
  UpdateError(100003,"更新失败"),
  DeleteError(100004,"删除失败"),
  QueryError(100005,"查询失败"),
  ValidateError(100006,"校验失败"),
  StatusValid(100007,"状态已启用"),
  StatusInValid(100008,"状态已禁用"),
  TransferStatusError(100009, "当前状态错误，请勿重复提交"),
  NotGrant(100010,"无操作该功能的权限，请联系管理员"),
  MethodArgumentValidateError(100010,"无操作该功能的权限，请联系管理员"),
  FileUploadError(100011,"文件上传失败"),
  IllegalState(100012,"状态错误"),
  ;


  private final Integer code;
  private final String text;

  CommonBizExceptionEnum(Integer code, String text) {
    this.code = code;
    this.text = text;
  }

  public static Optional<CommonBizExceptionEnum> of(Integer code) {
    return Optional.ofNullable(AppEnum.parse(CommonBizExceptionEnum.class, code));
  }

}
