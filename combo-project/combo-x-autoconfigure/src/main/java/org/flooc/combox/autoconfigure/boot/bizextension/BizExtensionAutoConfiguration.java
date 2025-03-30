package org.flooc.combox.autoconfigure.boot.bizextension;

import org.flooc.combox.boot.bizextension.BizExtensionConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @author sujie
 * @since 1.0.0
 */
@AutoConfiguration
public class BizExtensionAutoConfiguration {

  @Import(BizExtensionConfiguration.class)
  static class EnableBizExtension{

  }

}
