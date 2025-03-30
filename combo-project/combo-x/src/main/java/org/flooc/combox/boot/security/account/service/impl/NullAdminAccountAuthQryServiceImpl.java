package org.flooc.combox.boot.security.account.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import org.flooc.combox.boot.security.account.model.AdminAccountAuthDetails;
import org.flooc.combox.boot.security.account.service.AdminAccountAuthQryService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * @author sujie
 * @since 1.0.0
 */
public class NullAdminAccountAuthQryServiceImpl implements AdminAccountAuthQryService {

	@Override
	public Optional<AdminAccountAuthDetails> queryAccount(String param)  {
		return Optional.of(nullDetails());
	}

	private AdminAccountAuthDetails nullDetails() {
		return new AdminAccountAuthDetails() {
			@Override
			public String getOpenId() {
				return null;
			}

			@Override
			public Map<String, Object> getAdditionalParameter() {
				return Collections.emptyMap();
			}

			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));
			}

			@Override
			public String getPassword() {
				return null;
			}

			@Override
			public String getUsername() {
				return "Anonymous_QRY_NULL";
			}

		};
	}

}
