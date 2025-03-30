package org.flooc.combox.boot.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author sujie
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "app.auth.jwt")
@Getter
@Setter
public class JwtProperties {

	private String keyId = "combo";

	private long expiresIn = 60 * 60;

	private String tokenType = "Bearer";

	private long refreshTokenExpiresIn = 48 * 60 * 60;

}
