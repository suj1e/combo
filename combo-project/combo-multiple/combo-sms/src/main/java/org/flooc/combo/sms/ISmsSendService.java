package org.flooc.combo.sms;

import org.springframework.plugin.core.Plugin;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface ISmsSendService extends Plugin<SmsProducerType> {

	String PLUGIN_BEAN_NAME="smsSendServicePlugin";

	boolean sendSms(SmsSendModel model);

}
