package org.flooc.combo.sms.verifycode;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.flooc.combo.sms.verifycode.check.VerifyCodeSendModel;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author sujie
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class VerifyCodeCache {

	public static final String VERIFY_CODE_CACHE_PREFIX = "sms:verifyCode";

	private static final String VERIFY_CODE_CACHE_PATTERN = VERIFY_CODE_CACHE_PREFIX + ":%s";

	private final StringRedisTemplate smsRedisTemplate;

	public void cache(VerifyCodeSendModel dto) {
		smsRedisTemplate.opsForValue()
			.set(key(dto.getPhone()), String.valueOf(dto.getVerifyCodeTemplate().getCode()),
					Duration.ofMinutes(dto.getVerifyCodeTemplate().getTime()));
	}

	public String get(String phone) {
		return smsRedisTemplate.opsForValue().get(key(phone));
	}

	private String key(String phone) {
		return String.format(VERIFY_CODE_CACHE_PATTERN, phone);
	}

}
