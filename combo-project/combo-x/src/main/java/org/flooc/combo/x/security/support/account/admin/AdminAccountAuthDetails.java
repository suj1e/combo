package org.flooc.combo.x.security.support.account.admin;

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
