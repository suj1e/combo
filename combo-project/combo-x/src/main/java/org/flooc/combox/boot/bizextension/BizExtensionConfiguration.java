package org.flooc.combox.boot.bizextension;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author sujie
 * @since 1.0.0
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(BizExtensionProperties.class)
public class BizExtensionConfiguration {

}
