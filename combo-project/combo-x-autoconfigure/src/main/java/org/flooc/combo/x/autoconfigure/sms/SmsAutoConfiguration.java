package org.flooc.combo.x.autoconfigure.sms;

import org.flooc.combo.x.sms.SmsConfiguration;
import org.flooc.combo.x.sms.impl.ali.AliyunSmsConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author sujie
 * @since 1.0.0
 */
@AutoConfiguration
public class SmsAutoConfiguration {

	@ConditionalOnBean(StringRedisTemplate.class)
	@Import({SmsConfiguration.class, AliyunSmsConfiguration.class})
	static class EnableSmsConfiguration {

	}


}
