package org.flooc.combo.sms.verifycode.check;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.flooc.combo.sms.verifycode.VerifyCodeTemplate;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
public class VerifyCodeSendModel {

	private String phone;

	private VerifyCodeTemplate verifyCodeTemplate;

}
