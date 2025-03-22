package org.flooc.combo.x.oss;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sujie
 * @since 1.0.0
 */
@Configuration
@EnableConfigurationProperties({ OssProperties.class, OssBucketProperties.class })
public class OssConfiguration {

	@Bean
	@ConditionalOnMissingBean(AmazonS3.class)
	@ConditionalOnProperty(prefix = "app.oss", name = "enabled", havingValue = "true", matchIfMissing = true)
	public AmazonS3 amazonS3(OssProperties ossProperties) {
		return OssClientFactory.createAmazonS3(ossProperties);
	}

	@Bean
	@ConditionalOnMissingBean(S3OssClient.class)
	public OssClient ossClient(AmazonS3 amazonS3) {
		return new S3OssClient(amazonS3);
	}

}
