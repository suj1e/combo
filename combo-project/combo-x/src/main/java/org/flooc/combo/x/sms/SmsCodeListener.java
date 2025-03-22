package org.flooc.combo.x.sms;

import lombok.RequiredArgsConstructor;
import org.flooc.combo.x.sms.check.SmsCodeCheckModel;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.lang.NonNull;

/**
 * @author sujie
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class SmsCodeListener implements SmartApplicationListener {

	private final StringRedisTemplate redisTemplate;

	@Override
	public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
		return SmsCodeEvent.class.isAssignableFrom(eventType);
	}

	@Override
	public void onApplicationEvent(@NonNull ApplicationEvent event) {
		SmsCodeEvent smsCodeEvent = (SmsCodeEvent) event;
		Object source = smsCodeEvent.getSource();
		if (source instanceof SmsCodeCheckModel dto) {
			// 触发缓存
			SmsCodeCache cache = new SmsCodeCache(redisTemplate);
			cache.cache(dto);
		}
	}

}
