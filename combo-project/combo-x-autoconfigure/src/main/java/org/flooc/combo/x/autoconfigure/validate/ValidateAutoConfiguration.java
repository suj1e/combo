package org.flooc.combo.x.autoconfigure.validate;

import org.flooc.combo.x.validate.ValidateConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @author sujie
 * @since 1.0.0
 */
@AutoConfiguration
public class ValidateAutoConfiguration {


  @Import(ValidateConfiguration.class)
  static class EnableValidateConfiguration {

  }

}
