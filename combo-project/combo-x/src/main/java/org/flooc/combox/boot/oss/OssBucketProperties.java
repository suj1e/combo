package org.flooc.combox.boot.oss;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author sujie
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "app.oss.bucket")
@Getter
@Setter
public class OssBucketProperties {

	/**
	 * 公共bucket，可以公共访问，无需密钥
	 */
	private String publicDefaultBucket;

	/**
	 * 私有bucket，不可以公共访问，需密钥
	 */
	private String privateDefaultBucket;

}
