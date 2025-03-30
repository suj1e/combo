package org.flooc.combox.boot.sms.producer.ali;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.sms.message.aliyun")
@Getter
@Setter
public class AliyunSmsProperties {

	private String accessKeyId;

	private String accessKeySecret;

	private SmsProperties sms;

	@Getter
	@Setter
	public static class SmsProperties {

		private String region;

		private String endpoint;

		private String signName;

		private String templateCode;

		private int expiredTime;

	}

}
