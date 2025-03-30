package org.flooc.combo.security.util;

import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author sujie
 * @since 1.0.0
 */
public final class SecurityUtils {

	private SecurityUtils() {
	}

	public static Optional<String> getCurrentUserJWT() {
		SecurityContext context = SecurityContextHolder.getContext();
		return Optional.ofNullable(context.getAuthentication())
				.filter(authentication -> authentication.getCredentials() instanceof String)
				.map(authentication -> (String) authentication.getCredentials());
	}

	public static Optional<String> getCurrentUserLogin() {
		SecurityContext context = SecurityContextHolder.getContext();
		return Optional.ofNullable(extractPrincipal(context.getAuthentication()));
	}

	private static String extractPrincipal(Authentication authentication) {
		if (authentication == null) {
			return null;
		}
		Object principal = authentication.getPrincipal();
		if (principal instanceof String username) {
			return username;
		}
		return null;
	}

}
