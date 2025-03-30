package org.flooc.combox.boot.security.admin.password;

import java.io.Serial;
import java.util.Collection;
import lombok.Getter;
import org.flooc.combo.security.jwt.JwtAuthenticationToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

/**
 * <p>
 * 1.此类security filter chain的认证对象的封装
 * </p>
 *
 * @author sujie
 * @since 1.0.0
 */
public class PasswordAdminAuthenticationToken extends AbstractAuthenticationToken {

	@Serial
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	private Object principal;

	private Object credentials;

	@Getter
	private final String username;

	@Getter
	private final String password;

	public PasswordAdminAuthenticationToken(PasswordAdminLoginRequest loginRequest) {
		super(null);
		this.username = loginRequest.getUsername();
		this.password = loginRequest.getPassword();
		setAuthenticated(false);
	}

	public PasswordAdminAuthenticationToken(Object principal, Object credentials,
			PasswordAdminAuthenticationToken unauthenticatedToken, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.username = unauthenticatedToken.getUsername();
		this.password = unauthenticatedToken.getPassword();
		this.principal = principal;
		this.credentials = credentials;
		setAuthenticated(true);
	}

	public static JwtAuthenticationToken toJwtToken(PasswordAdminAuthenticationToken authenticatedToken) {
		JwtAuthenticationToken token = new JwtAuthenticationToken(authenticatedToken.getPrincipal(),
				authenticatedToken.getCredentials(), authenticatedToken.getAuthorities());
		token.setAuthenticated(authenticatedToken.isAuthenticated());
		token.setDetails(authenticatedToken.getDetails());
		return token;
	}

	public static PasswordAdminAuthenticationToken unauthenticated(PasswordAdminLoginRequest loginRequest) {
		return new PasswordAdminAuthenticationToken(loginRequest);
	}

	public static PasswordAdminAuthenticationToken authenticated(Object principal, Object credentials,
			PasswordAdminAuthenticationToken unauthenticatedToken, Collection<? extends GrantedAuthority> authorities) {
		return new PasswordAdminAuthenticationToken(principal, credentials, unauthenticatedToken, authorities);
	}

	@Override
	public Object getCredentials() {
		return this.credentials;
	}

	@Override
	public Object getPrincipal() {
		return this.principal;
	}

}
