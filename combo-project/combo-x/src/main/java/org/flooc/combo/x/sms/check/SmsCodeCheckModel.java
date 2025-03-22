package org.flooc.combo.x.sms.check;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.flooc.combo.x.sms.SmsCodeTemplate;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
public class SmsCodeCheckModel {

	private String phone;

	private SmsCodeTemplate smsCodeTemplate;

}
