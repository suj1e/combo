package org.flooc.combo.x.sms.check;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flooc.combo.x.sms.SmsCodeCache;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

/**
 * @author sujie
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Slf4j
public class SmsVerifyCodeCheckerImpl implements SmsVerifyCodeChecker {

	private final StringRedisTemplate redisTemplate;

	@Override
	public SmsCodeCheckResult check(VerifyCodeCheckModel dto) {
		SmsCodeCache cache = new SmsCodeCache(redisTemplate);
		String cacheCode = cache.get(dto.getPhone());
		SmsCodeCheckResult result = new SmsCodeCheckResult();
		if (StringUtils.hasText(cacheCode) && cacheCode.equals(dto.getVerifyCode())) {
			result.setValid(true);
		}
		else {
			if (log.isDebugEnabled()) {
				log.debug("The verification code for {} has expired or be removed", dto.getPhone());
			}
			result.setValid(false);
		}
		return result;
	}

}