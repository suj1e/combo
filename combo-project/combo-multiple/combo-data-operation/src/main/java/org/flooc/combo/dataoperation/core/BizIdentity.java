package org.flooc.combo.dataoperation.core;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface BizIdentity {

  /**
   * 例如<br/> 订单: ORD
   *
   * @return 业务标识
   */
  default String bizIdentity() {
    return null;
  }

  String getId();

  /**
   * <p>
   * 例如<br/> 订单号：ORD20250329123456（时间 + 序列号）<br/> 交易流水号：TX20250329SH0001（业务 + 时间 + 地域 + 流水）<br/>
   * 物流单号：SF20250329001X（快递公司 + 时间 + 校验码）
   * </p>
   *
   * @return 业务流水号
   */
  default String getBizNo() {
    return null;
  }

}
