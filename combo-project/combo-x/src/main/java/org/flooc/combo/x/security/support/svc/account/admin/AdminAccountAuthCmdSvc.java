package org.flooc.combo.x.security.support.svc.account.admin;

import org.flooc.combo.x.security.support.account.admin.AdminAccountAuthDetails;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface AdminAccountAuthCmdSvc {

	AdminAccountAuthDetails registerNormalAccountByPhone(String phone);

}
