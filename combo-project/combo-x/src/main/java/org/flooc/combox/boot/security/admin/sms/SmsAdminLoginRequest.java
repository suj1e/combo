package org.flooc.combox.boot.security.admin.sms;

import lombok.Getter;
import lombok.Setter;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
@Setter
public class SmsAdminLoginRequest {

	private String phone;

	private String verifyCode;

}
