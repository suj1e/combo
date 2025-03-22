package org.flooc.combo.x.security.support.svc.account.admin.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import org.flooc.combo.x.security.support.account.admin.AdminAccountAuthDetails;
import org.flooc.combo.x.security.support.svc.account.admin.AdminAccountAuthQrySvc;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * @author sujie
 * @since 1.0.0
 */
public class NullAdminAccountAuthQrySvcImpl implements AdminAccountAuthQrySvc {

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
