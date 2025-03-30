package org.flooc.combox.boot.oss;

import lombok.Builder;
import lombok.Getter;

/**
 * @author sujie
 * @since 1.0.0
 */
@Builder
@Getter
public class OssTransferModel {

  private String bucketName;
  private String objectName;
  private byte[] bytes;
  private Long size;
  private String contentType;
  private boolean autoClose;


}
