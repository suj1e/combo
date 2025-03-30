package org.flooc.combox.boot.oss;

import com.aliyun.oss.OSSClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sujie
 * @since 1.0.0
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({OssProperties.class, OssBucketProperties.class})
public class OssConfiguration {


  @Bean
  @ConditionalOnClass(OSSClient.class)
  public IOssService aliyunOssService(OssProperties ossProperties) {
    return new AliyunOssServiceImpl(ossProperties);
  }

}
