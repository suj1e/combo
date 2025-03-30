package org.flooc.combo.sms;

import lombok.RequiredArgsConstructor;
import org.flooc.combo.sms.verifycode.check.VerifyCodeSendModel;
import org.flooc.combo.sms.verifycode.VerifyCodeCache;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.lang.NonNull;

/**
 * @author sujie
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class SmsCodeEventProcessor implements SmartApplicationListener {

	private final StringRedisTemplate redisTemplate;

	@Override
	public boolean supportsEventType(@NonNull Class<? extends ApplicationEvent> eventType) {
		return SmsCodeEvent.class.isAssignableFrom(eventType);
	}

	@Override
	public void onApplicationEvent(@NonNull ApplicationEvent event) {
		SmsCodeEvent smsCodeEvent = (SmsCodeEvent) event;
		Object source = smsCodeEvent.getSource();
		if (source instanceof VerifyCodeSendModel dto) {
			// 触发缓存
			VerifyCodeCache cache = new VerifyCodeCache(redisTemplate);
			cache.cache(dto);
		}
	}

}
