package org.flooc.combox.boot.security.admin.password;

import lombok.Getter;
import lombok.Setter;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
@Setter
public class PasswordAdminLoginRequest {

	private String username;

	private String password;

}
