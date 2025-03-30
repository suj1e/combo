package org.flooc.combox.boot.dataoperation.jpa;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;

/**
 * @author sujie
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class DefaultJpaAuditorAware implements AuditorAware<String> {

	private final String defaultAuditor;

	@Override
	@NonNull
	public Optional<String> getCurrentAuditor() {
		return Optional.of(defaultAuditor);
	}

}
