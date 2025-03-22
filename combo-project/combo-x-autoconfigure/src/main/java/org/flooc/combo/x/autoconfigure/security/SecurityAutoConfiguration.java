package org.flooc.combo.x.autoconfigure.security;

import org.flooc.combo.x.security.config.SecurityConfiguration;
import org.flooc.combo.x.security.config.SecurityFilterChainConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * @author sujie
 * @since 1.0.0
 */
@AutoConfiguration
@ConditionalOnClass(HttpSecurity.class)
public class SecurityAutoConfiguration {

  @Import({SecurityConfiguration.class, SecurityFilterChainConfiguration.class})
  static class EnableSecurityConfig {

  }


}
