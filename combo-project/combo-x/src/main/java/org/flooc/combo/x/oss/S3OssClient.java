package org.flooc.combo.x.oss;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author sujie
 * @since 1.0.0
 */
public record S3OssClient(AmazonS3 amazonS3) implements OssClient {

	@Override
	public void createBucket(String bucketName) {
		if (!amazonS3.doesBucketExistV2(bucketName)) {
			amazonS3.createBucket(bucketName);
		}
	}

	@Override
	public String getObjectUrl(String bucketName, String objectName) {
		return amazonS3.getUrl(bucketName, objectName).toString();
	}

	@Override
	public S3Object getObjectInfo(String bucketName, String objectName) {
		return amazonS3.getObject(bucketName, objectName);
	}

	@Override
	public PutObjectResult putObject(String bucketName, String objectName, InputStream inputStream, Long size,
			String contentType) throws IOException {
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(size);
		objectMetadata.setContentType(contentType);
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream, objectMetadata);
		putObjectRequest.getRequestClientOptions().setReadLimit(size.intValue() + 1);
		return amazonS3.putObject(putObjectRequest);
	}

}
