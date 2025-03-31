package org.flooc.combox.boot.bizextension;

import lombok.Getter;
import lombok.Setter;
import org.flooc.combo.serialnumber.SerialNumberType;
import org.flooc.combo.sms.SmsProducerType;
import org.flooc.combox.boot.oss.OssServiceType;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author sujie
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "app.biz-extension.service-type")
@Getter
@Setter
public class BizExtensionProperties {

  /**
   * @see SmsProducerType#getCode()
   */
  private Integer sms;
  /**
   * @see OssServiceType#getCode()
   */
  private Integer oss;
  /**
   * @see SerialNumberType#getCode()
   */
  private Integer serialNumber;

}
