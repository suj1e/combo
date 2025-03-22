package org.flooc.combo.x.sms.check;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface SmsVerifyCodeChecker {

	SmsCodeCheckResult check(VerifyCodeCheckModel dto);

}
