package org.flooc.combo.sms.verifycode.check;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flooc.combo.sms.verifycode.VerifyCodeCache;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

/**
 * @author sujie
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Slf4j
public class VerifyCodeServiceImpl implements IVerifyCodeService {

	private final StringRedisTemplate redisTemplate;

	@Override
	public VerifyCodeCheckResult check(VerifyCodeCheckModel model) {
		VerifyCodeCache cache = new VerifyCodeCache(redisTemplate);
		String cacheCode = cache.get(model.getPhone());
		VerifyCodeCheckResult result = new VerifyCodeCheckResult();
		if (StringUtils.hasText(cacheCode) && cacheCode.equals(model.getVerifyCode())) {
			result.setValid(true);
		}
		else {
			if (log.isDebugEnabled()) {
				log.debug("The verification code for {} has expired or be removed", model.getPhone());
			}
			result.setValid(false);
		}
		return result;
	}

}