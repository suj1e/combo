package org.flooc.combo.x.oss;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface OssClient {

	void createBucket(String bucketName);

	String getObjectUrl(String bucketName, String objectName);

	S3Object getObjectInfo(String bucketName, String objectName);

	PutObjectResult putObject(String bucketName, String objectName, InputStream inputStream, Long size,
			String contentType) throws IOException;

	AmazonS3 amazonS3();

}
