package org.flooc.combox.boot.security.admin.password;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.flooc.combo.common.exception.CmpRuntimeException;
import org.flooc.combo.security.exception.SecurityErrorCode;
import org.flooc.combox.boot.security.account.model.AdminAccountAuthDetails;
import org.flooc.combox.boot.security.account.service.AdminAccountAuthQryService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

/**
 * <p>
 * 1.AuthenticationManager委托给provider执行具体的认证 2.封装认证成功后的token（包含了用户的权限信息）
 * </p>
 *
 * @author sujie
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class PasswordAdminAuthenticationProvider implements AuthenticationProvider, InitializingBean {

	private final PasswordEncoder passwordEncoder;

	private final AdminAccountAuthQryService adminAccountAuthQryService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		PasswordAdminAuthenticationToken unauthenticatedToken = (PasswordAdminAuthenticationToken) authentication;
		// 1.获取用户信息2.密码比对3.输出token和错误处理
		String username = unauthenticatedToken.getUsername();
		Optional<AdminAccountAuthDetails> authDetailsOptional = adminAccountAuthQryService.queryAccount(username);
		if (authDetailsOptional.isEmpty()) {
			throw new CmpRuntimeException(SecurityErrorCode.NoSuchUser);
		}
		AdminAccountAuthDetails authAccountDetails = authDetailsOptional.get();
		String encodedPassword = authAccountDetails.getPassword();
		if (!passwordEncoder.matches(unauthenticatedToken.getPassword(), encodedPassword)) {
			throw new CmpRuntimeException(SecurityErrorCode.PasswordIncorrect);
		}
		return createSuccessAuthentication(unauthenticatedToken, authAccountDetails);
	}

	private Authentication createSuccessAuthentication(PasswordAdminAuthenticationToken authentication,
			AdminAccountAuthDetails adminAccountAuthDetails) {
		return PasswordAdminAuthenticationToken.authenticated(adminAccountAuthDetails.getUsername(),
				adminAccountAuthDetails.getPassword(), authentication, adminAccountAuthDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return PasswordAdminAuthenticationToken.class.isAssignableFrom(authentication);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(passwordEncoder, "PasswordEncoder must not be null");
		Assert.notNull(adminAccountAuthQryService, "AdminUserQrySvc must not be null");
	}

}
