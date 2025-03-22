package org.flooc.combo.x.security.common;

import lombok.Getter;
import lombok.Setter;
import org.flooc.combo.x.constant.CmpExceptionConstant.SecurityExceptionEnum;

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

	private SecurityExceptionEnum securityExceptionEnum;

	public static JwtTokenVerifyResult valid() {
		JwtTokenVerifyResult result = new JwtTokenVerifyResult();
		result.setValid(true);
		return result;
	}

	public static JwtTokenVerifyResult invalid(SecurityExceptionEnum securityExceptionEnum) {
		JwtTokenVerifyResult result = new JwtTokenVerifyResult();
		result.setValid(false);
		result.setSecurityExceptionEnum(securityExceptionEnum);
		return result;
	}

}
