package org.flooc.combo.sms;

import org.springframework.context.ApplicationEvent;

/**
 * @author sujie
 * @since 1.0.0
 */
public class SmsCodeEvent extends ApplicationEvent {

  public SmsCodeEvent(Object source) {
    super(source);
  }

}
