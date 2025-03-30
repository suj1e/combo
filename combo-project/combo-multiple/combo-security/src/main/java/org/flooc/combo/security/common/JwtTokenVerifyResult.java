package org.flooc.combo.security.common;

import lombok.Getter;
import lombok.Setter;
import org.flooc.combo.security.exception.SecurityErrorCode;

/**
 * 令牌验证结果
 *
 * @author sujie
 * @since 1.0.0
 */
@Getter
@Setter
public class JwtTokenVerifyResult {

	private boolean valid;

	private SecurityErrorCode securityErrorCode;

	public static JwtTokenVerifyResult valid() {
		JwtTokenVerifyResult result = new JwtTokenVerifyResult();
		result.setValid(true);
		return result;
	}

	public static JwtTokenVerifyResult invalid(SecurityErrorCode securityErrorCode) {
		JwtTokenVerifyResult result = new JwtTokenVerifyResult();
		result.setValid(false);
		result.setSecurityErrorCode(securityErrorCode);
		return result;
	}

}
