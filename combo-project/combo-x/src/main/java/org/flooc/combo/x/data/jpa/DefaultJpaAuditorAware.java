package org.flooc.combo.x.data.jpa;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;

/**
 * @author sujie
 * @since 1.0.0
 */
public class DefaultJpaAuditorAware implements AuditorAware<String> {

	@Override
	@NonNull
	public Optional<String> getCurrentAuditor() {
		return Optional.of("PLATFORM_DEFAULT");
	}

}
