package org.flooc.combox.boot.oss;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.comm.SignVersion;
import java.util.Objects;
import java.util.stream.Stream;
import org.springframework.util.Assert;

/**
 * @author sujie
 * @since 1.0.0
 */
public class AliyunOssClientFactory {

  public static OSS createAliyunClient(OssProperties ossProperties) {
    long nullSize = Stream.builder()
        .add(ossProperties.getEndpoint())
        .add(ossProperties.getAccessKey())
        .add(ossProperties.getAccessSecret())
        .build()
        .filter(Objects::isNull)
        .count();
    Assert.state(nullSize <= 0, "ossProperties is not complete");
    DefaultCredentialProvider defaultCredentialProvider = new DefaultCredentialProvider(
        ossProperties.getAccessKey(), ossProperties.getAccessSecret());
    ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
    // 显式声明使用 V4 签名算法
    clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
    return OSSClientBuilder.create()
        .endpoint(ossProperties.getEndpoint())
        .credentialsProvider(defaultCredentialProvider)
        .region(ossProperties.getRegion())
        .build();
  }

}
