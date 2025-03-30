package org.flooc.combox.boot.security.account.service;

import java.util.Optional;
import org.flooc.combox.boot.security.account.model.AdminAccountAuthDetails;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface AdminAccountAuthQryService {

	/**
	 * @param param 用户名或密码
	 * @return 认证账户信息
	 */
	Optional<AdminAccountAuthDetails> queryAccount(String param);

}
