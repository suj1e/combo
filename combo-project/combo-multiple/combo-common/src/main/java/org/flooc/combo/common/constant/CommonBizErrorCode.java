package org.flooc.combo.common.constant;

import java.util.Optional;
import lombok.Getter;

/**
 * 公共-业务异常码
 *
 * @author sujie
 * @since 1.0.0
 */
@Getter
public enum CommonBizErrorCode implements BizErrorCode<CommonBizErrorCode> {

  AppInternalError(100000, "系统异常"),
  RpcError(100001, "远程调用异常"),
  BizError(100002, "业务异常"),
  CreateError(100003, "新增失败"),
  UpdateError(100004, "更新失败"),
  DeleteError(100005, "删除失败"),
  QueryError(100006, "查询失败"),
  ValidateError(100007, "校验失败"),
  StatusValid(100008, "状态已启用"),
  StatusInValid(100009, "状态已禁用"),
  TransferStatusError(100010, "当前状态错误，请勿重复提交"),
  NotGrant(100011, "无操作该功能的权限，请联系管理员"),
  MethodArgumentValidateError(100012, "无操作该功能的权限，请联系管理员"),
  FileUploadError(100013, "文件上传失败"),
  IllegalState(100014, "状态错误"),
  NotFindData(100015, "没有查询到数据"),
  DispatchExecuteError(100016, "派发执行失败"),
  ;


  private final Integer code;
  private final String text;

  CommonBizErrorCode(Integer code, String text) {
    this.code = code;
    this.text = text;
  }

  public static Optional<CommonBizErrorCode> of(Integer code) {
    return Optional.ofNullable(AppCode.parse(CommonBizErrorCode.class, code));
  }

}
