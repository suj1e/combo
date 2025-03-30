package org.flooc.combox.boot.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.OSSObject;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

/**
 * @author sujie
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class AliyunOssServiceImpl implements IOssService {

  private final OssProperties ossProperties;

  @Override
  public void createBucket(String bucketName) {
    OSS ossClient = AliyunOssClientFactory.createAliyunClient(ossProperties);
    try {
      ossClient.createBucket(bucketName);
    } finally {
      ossClient.shutdown();
    }
  }

  @Override
  public void putObject(OssTransferModel ossTransferModel) {
    OSS ossClient = AliyunOssClientFactory.createAliyunClient(ossProperties);
    try {
      ossClient.putObject(ossTransferModel.getBucketName(), ossTransferModel.getObjectName(),
          new ByteArrayInputStream(ossTransferModel.getBytes()));
    } finally {
      ossClient.shutdown();
    }
  }

  @Override
  public void putBatchObject(List<OssTransferModel> models) {
    OSS ossClient = AliyunOssClientFactory.createAliyunClient(ossProperties);
    try {
      for (OssTransferModel ossTransferModel : models) {
        ossClient.putObject(ossTransferModel.getBucketName(), ossTransferModel.getObjectName(),
            new ByteArrayInputStream(ossTransferModel.getBytes()));
      }
    } finally {
      ossClient.shutdown();
    }
  }

  @Override
  public InputStream downloadFile(String bucketName, String objectName) throws IOException {
    OSS ossClient = AliyunOssClientFactory.createAliyunClient(ossProperties);
    OSSObject ossObject = ossClient.getObject(bucketName, objectName);
    try (InputStream objectContent = ossObject.getObjectContent()) {
      return objectContent;
    } finally {
      ossClient.shutdown();
    }
  }

  @Override
  public void deleteFile(String bucketName, String objectName) {
    OSS ossClient = AliyunOssClientFactory.createAliyunClient(ossProperties);
    try {
      ossClient.deleteObject(bucketName, objectName);
    } finally {
      ossClient.shutdown();
    }
  }

  @Override
  public void deleteBucket(String bucketName) {
    OSS ossClient = AliyunOssClientFactory.createAliyunClient(ossProperties);
    try {
      ossClient.deleteBucket(bucketName);
    } finally {
      ossClient.shutdown();
    }
  }

  @Override
  public boolean supports(@NonNull OssServiceType delimiter) {
    return OssServiceType.ALIYUN.equals(delimiter);
  }
}
