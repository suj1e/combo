package org.flooc.combox.boot.idempotent;

import lombok.Data;

/**
 * @author sujie
 * @since 1.0.0
 */
@Data
public class IdempotentModel {

	private String requestUri;

	private String ip;

}
