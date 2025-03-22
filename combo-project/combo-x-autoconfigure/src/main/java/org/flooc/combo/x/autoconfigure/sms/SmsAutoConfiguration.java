package org.flooc.combo.x.autoconfigure.sms;

import org.flooc.combo.x.sms.SmsConfiguration;
import org.flooc.combo.x.sms.impl.ali.AliyunSmsConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @author sujie
 * @since 1.0.0
 */
@AutoConfiguration
public class SmsAutoConfiguration {

	@Import({SmsConfiguration.class, AliyunSmsConfiguration.class})
	static class EnableSmsConfiguration {

	}


}
