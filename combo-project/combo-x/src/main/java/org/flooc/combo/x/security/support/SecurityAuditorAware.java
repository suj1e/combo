package org.flooc.combo.x.security.support;

import java.util.Optional;
import org.flooc.combo.x.security.util.SecurityUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;

/**
 * @author sujie
 * @since 1.0.0
 */
public class SecurityAuditorAware implements AuditorAware<String> {

	@Override
	@NonNull
	public Optional<String> getCurrentAuditor() {
		return Optional.of(SecurityUtils.getCurrentUserLogin().orElse("PLATFORM_ADMIN"));
	}

}
