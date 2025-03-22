package org.flooc.combo.x.oss;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import java.util.Objects;
import java.util.stream.Stream;
import org.springframework.util.Assert;

/**
 * @author sujie
 * @since 1.0.0
 */
public class OssClientFactory {

	public static AmazonS3 createAmazonS3(OssProperties ossProperties) {
		long nullSize = Stream.builder()
			.add(ossProperties.getEndpoint())
			.add(ossProperties.getAccessKey())
			.add(ossProperties.getAccessSecret())
			.build()
			.filter(Objects::isNull)
			.count();
		Assert.state(nullSize <= 0, "ossProperties is not complete");
		AWSCredentials awsCredentials = new BasicAWSCredentials(ossProperties.getAccessKey(),
				ossProperties.getAccessSecret());
		AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
		return AmazonS3Client.builder()
			.withEndpointConfiguration(
					new EndpointConfiguration(ossProperties.getEndpoint(), ossProperties.getRegion()))
			.withCredentials(awsCredentialsProvider)
			.disableChunkedEncoding()
			.withPathStyleAccessEnabled(ossProperties.isPathStyleAccess())
			.build();
	}

}
