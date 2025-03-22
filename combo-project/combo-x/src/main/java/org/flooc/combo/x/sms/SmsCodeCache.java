package org.flooc.combo.x.sms;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.flooc.combo.x.sms.check.SmsCodeCheckModel;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author sujie
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class SmsCodeCache {

	public static final String SMS_CODE_CACHE_PREFIX = "sms:verifyCode";

	private static final String SMS_CODE_CACHE_PATTERN = SMS_CODE_CACHE_PREFIX + ":%s";

	private final StringRedisTemplate smsRedisTemplate;

	public void cache(SmsCodeCheckModel dto) {
		smsRedisTemplate.opsForValue()
			.set(key(dto.getPhone()), String.valueOf(dto.getSmsCodeTemplate().getCode()),
					Duration.ofMinutes(dto.getSmsCodeTemplate().getTime()));
	}

	public String get(String phone) {
		return smsRedisTemplate.opsForValue().get(key(phone));
	}

	private String key(String phone) {
		return String.format(SMS_CODE_CACHE_PATTERN, phone);
	}

}
