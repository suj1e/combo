package org.flooc.combox.boot.security.admin.sms;

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
public class SmsAdminAuthenticationToken extends AbstractAuthenticationToken {

	@Serial
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	private Object principal;

	private Object credentials;

	@Getter
	private final String phone;

	@Getter
	private final String verifyCode;

	public SmsAdminAuthenticationToken(SmsAdminLoginRequest loginRequest) {
		super(null);
		this.phone = loginRequest.getPhone();
		this.verifyCode = loginRequest.getVerifyCode();
		setAuthenticated(false);
	}

	public SmsAdminAuthenticationToken(Object principal, Object credentials,
			SmsAdminAuthenticationToken unauthenticatedToken, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.phone = unauthenticatedToken.getPhone();
		this.verifyCode = unauthenticatedToken.getVerifyCode();
		this.principal = principal;
		this.credentials = credentials;
		setAuthenticated(true);
	}

	public static JwtAuthenticationToken toJwtToken(SmsAdminAuthenticationToken authenticatedToken) {
		JwtAuthenticationToken token = new JwtAuthenticationToken(authenticatedToken.getPrincipal(),
				authenticatedToken.getCredentials(), authenticatedToken.getAuthorities());
		token.setAuthenticated(authenticatedToken.isAuthenticated());
		token.setDetails(authenticatedToken.getDetails());
		return token;
	}

	public static SmsAdminAuthenticationToken unauthenticated(SmsAdminLoginRequest loginRequest) {
		return new SmsAdminAuthenticationToken(loginRequest);
	}

	public static SmsAdminAuthenticationToken authenticated(Object principal, Object credentials,
			SmsAdminAuthenticationToken unauthenticatedToken, Collection<? extends GrantedAuthority> authorities) {
		return new SmsAdminAuthenticationToken(principal, credentials, unauthenticatedToken, authorities);
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
