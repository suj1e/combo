package org.flooc.combox.boot.security.account.service;

import org.flooc.combox.boot.security.account.model.AdminAccountAuthDetails;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface AdminAccountAuthCmdService {

	AdminAccountAuthDetails registerNormalAccountByPhone(String phone);

}
