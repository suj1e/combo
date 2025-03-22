package org.flooc.combo.x.autoconfigure.oss;

import com.amazonaws.services.s3.AmazonS3;
import org.flooc.combo.x.oss.OssConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Import;

/**
 * @author sujie
 * @since 1.0.0
 */
@AutoConfiguration
@ConditionalOnClass(AmazonS3.class)
public class OssAutoConfiguration {

	@Import(OssConfiguration.class)
	static class EnableOssConfiguration {

	}

}
