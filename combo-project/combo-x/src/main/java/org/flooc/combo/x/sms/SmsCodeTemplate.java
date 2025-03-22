package org.flooc.combo.x.sms;

import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;

@Getter
public class SmsCodeTemplate {

	private final int code;

	private final int time;

	public SmsCodeTemplate(int time) {
		this.code = ThreadLocalRandom.current().nextInt(1000, 10000);
		this.time = time;
	}

}
