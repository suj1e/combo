package org.flooc.combox.boot.oss;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author sujie
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "app.oss")
@Getter
@Setter
public class OssProperties {

	private boolean enabled = true;

	private String endpoint;

	private String accessKey;

	private String accessSecret;

	private String region;

	/**
	 * @see OssServiceType#getCode()
	 */
	private String serviceTypeCode;

}
