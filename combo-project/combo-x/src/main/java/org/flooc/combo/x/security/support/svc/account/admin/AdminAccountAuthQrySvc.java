package org.flooc.combo.x.security.support.svc.account.admin;

import java.util.Optional;
import org.flooc.combo.x.security.support.account.admin.AdminAccountAuthDetails;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface AdminAccountAuthQrySvc {

	/**
	 * @param param 用户名或密码
	 * @return 认证账户信息
	 */
	Optional<AdminAccountAuthDetails> queryAccount(String param);

}
