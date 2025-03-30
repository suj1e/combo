package org.flooc.combo.serialnumber;

import org.springframework.plugin.core.Plugin;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface SerialNumberService extends Plugin<SerialNumberType> {

  String PLUGIN_BEAN_NAME = "serialNumberServicePlugin";

  String nextBizNo();

  String nextBizNo(String bizIdentity);

}
