package org.flooc.combox.boot.security.account.model;

import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface AdminAccountAuthDetails extends UserDetails {

	String getOpenId();

	Map<String, Object> getAdditionalParameter();

}
