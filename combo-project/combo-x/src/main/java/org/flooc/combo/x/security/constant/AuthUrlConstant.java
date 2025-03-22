package org.flooc.combo.x.security.constant;

/**
 * @author sujie
 * @since 1.0.0
 */
public final class AuthUrlConstant {

	private AuthUrlConstant() {
	}

	public static final String SMS_ADMIN_LOGIN_DISPATCH_URL = "/admin/smsLogin";

	public static final String PASSWORD_ADMIN_LOGIN_DISPATCH_URL = "/admin/passwordLogin";

	public static final String REFRESH_TOKEN_DISPATCH_URL = "/jwt/refreshToken";

}
