package org.flooc.combo.x.sms.support;

import org.springframework.plugin.core.Plugin;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface ISmsSendService extends Plugin<SmsBizType> {

	boolean sendSms(SmsSendModel model);

}
