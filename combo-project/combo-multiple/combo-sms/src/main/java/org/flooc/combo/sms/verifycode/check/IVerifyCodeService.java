package org.flooc.combo.sms.verifycode.check;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface IVerifyCodeService {

	VerifyCodeCheckResult check(VerifyCodeCheckModel dto);

}
