package org.flooc.combox.boot.sms;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author sujie
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "app.sms.message")
@Getter
@Setter
public class SmsMessageProperties {

	/**
	 * 验证码长度
	 */
	private Integer verifyLength;

	/**
	 * 有效时间
	 */
	private Integer validTime;

	/**
	 * 发送间隔
	 */
	private Integer sendInterval;

	/**
	 * 发送MaxTimes，根据模版判断
	 */
	private Integer sendMaxTimes;

}
