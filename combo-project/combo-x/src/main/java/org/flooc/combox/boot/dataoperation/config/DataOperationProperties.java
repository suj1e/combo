package org.flooc.combox.boot.dataoperation.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author sujie
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "app.dataoperation")
@Getter
@Setter
public class DataOperationProperties {

	private Long workerId;
	private String defaultSecurityAuditor = "platformSecurityDefault";
	private String defaultAuditor = "platformDefault";

}
