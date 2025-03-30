package org.flooc.combo.dataoperation.validate;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sujie
 * @since 1.0.0
 */
@Configuration
public class ValidateConfiguration {

  @Bean
  public ValidateExecutor validateExecutor(List<Validator> validators) {
    return new ValidateExecutor(validators);
  }


}
