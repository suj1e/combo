package org.flooc.combo.sms.verifycode;

import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;

@Getter
public class VerifyCodeTemplate {

	private final int code;

	private final int time;

	public VerifyCodeTemplate(int time) {
		this.code = ThreadLocalRandom.current().nextInt(1000, 10000);
		this.time = time;
	}

}
