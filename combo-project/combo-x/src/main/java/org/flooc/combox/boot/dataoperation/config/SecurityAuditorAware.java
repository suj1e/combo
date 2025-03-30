package org.flooc.combox.boot.dataoperation.config;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.flooc.combo.security.util.SecurityUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;

/**
 * @author sujie
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class SecurityAuditorAware implements AuditorAware<String> {

	private final String defaultSecurityAuditor;

	@Override
	@NonNull
	public Optional<String> getCurrentAuditor() {
		return Optional.of(SecurityUtils.getCurrentUserLogin().orElse(defaultSecurityAuditor));
	}

}
