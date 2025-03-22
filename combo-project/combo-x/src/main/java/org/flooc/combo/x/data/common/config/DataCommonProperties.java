package org.flooc.combo.x.data.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author sujie
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "app.data.common")
@Getter
@Setter
public class DataCommonProperties {

	private Long workerId;

}
