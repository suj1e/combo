package org.flooc.combo.sms;

import java.util.List;
import java.util.Map;
import lombok.Data;

/**
 * @author sujie
 * @since 1.0.0
 */
@Data
public class SmsSendModel {

	private List<String> phones;

	private Map<String, String> params;

	private String templateCode;

}
