package org.flooc.combox.boot.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author sujie
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "app.auth")
@Getter
@Setter
public class AuthProperties {

	private boolean authorization;

	private boolean authentication;

	private String resourceAuthPattern = "/**";

	/**
	 * 全局放开权限的url
	 */
	private String[] webIgnoreUrls;

	private FilterChainIgnoreUrl jwtChainIgnore;

	@Getter
	@Setter
	public static class FilterChainIgnoreUrl {

		private String[] ignoreUrls;

	}

}
