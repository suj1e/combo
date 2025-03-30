package org.flooc.combox.autoconfigure.boot.sms;

import org.flooc.combo.sms.ISmsSendService;
import org.flooc.combox.boot.sms.SmsConfiguration;
import org.flooc.combox.boot.sms.producer.ali.AliyunSmsConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Import;

/**
 * @author sujie
 * @since 1.0.0
 */
@AutoConfiguration
public class SmsAutoConfiguration {

	@ConditionalOnClass(ISmsSendService.class)
	@Import({SmsConfiguration.class, AliyunSmsConfiguration.class})
	static class EnableSmsConfiguration {

	}


}
