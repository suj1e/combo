package org.flooc.combox.boot.oss;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.springframework.plugin.core.Plugin;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface IOssService extends Plugin<OssServiceType> {

  String PLUGIN_BEAN_NAME = "ossServicePlugin";

  void createBucket(String bucketName);

  void putObject(OssTransferModel transferModel);

  void putBatchObject(List<OssTransferModel> models);

  InputStream downloadFile(String bucketName, String objectName) throws IOException;


  void deleteFile(String bucketName, String objectName);

  void deleteBucket(String bucketName);


}
