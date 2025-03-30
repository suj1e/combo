package org.flooc.combox.boot.security.admin.sms;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flooc.combo.common.exception.CmpRuntimeException;
import org.flooc.combo.security.exception.SecurityErrorCode;
import org.flooc.combox.boot.security.account.model.AdminAccountAuthDetails;
import org.flooc.combox.boot.security.account.service.AdminAccountAuthCmdService;
import org.flooc.combox.boot.security.account.service.AdminAccountAuthQryService;
import org.flooc.combo.sms.verifycode.check.VerifyCodeCheckResult;
import org.flooc.combo.sms.verifycode.check.IVerifyCodeService;
import org.flooc.combo.sms.verifycode.check.VerifyCodeCheckModel;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.Assert;

/**
 * <p>
 * 1.AuthenticationManager委托给provider执行具体的认证 2.封装认证成功后的token（包含了用户的权限信息）
 * </p>
 *
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
public class SmsAdminAuthenticationProvider implements AuthenticationProvider, InitializingBean {

	private final IVerifyCodeService iVerifyCodeService;

	private final AdminAccountAuthQryService adminAccountAuthQryService;

	private final AdminAccountAuthCmdService adminAccountAuthCmdService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		SmsAdminAuthenticationToken unauthenticatedToken = (SmsAdminAuthenticationToken) authentication;
		VerifyCodeCheckModel dto = new VerifyCodeCheckModel();
		String phone = unauthenticatedToken.getPhone();
		dto.setPhone(phone);
		dto.setVerifyCode(unauthenticatedToken.getVerifyCode());
		VerifyCodeCheckResult check = iVerifyCodeService.check(dto);
		if (!check.isValid()) {
			throw new CmpRuntimeException(SecurityErrorCode.VerifyCodeIncorrect);
		}
		Optional<AdminAccountAuthDetails> authAccountOptional = adminAccountAuthQryService.queryAccount(phone);
		AdminAccountAuthDetails authAccountToUse;
		if (authAccountOptional.isEmpty()) {
			if (log.isDebugEnabled()) {
				log.debug("无此用户，验证码方式自动注册普通用户");
			}
			authAccountToUse = adminAccountAuthCmdService.registerNormalAccountByPhone(phone);
		}
		else {
			authAccountToUse = authAccountOptional.get();
		}
		return createSuccessAuthentication(unauthenticatedToken, authAccountToUse);
	}

	private Authentication createSuccessAuthentication(SmsAdminAuthenticationToken authentication,
			AdminAccountAuthDetails adminAccountAuthDetails) {
		return SmsAdminAuthenticationToken.authenticated(adminAccountAuthDetails.getUsername(),
				adminAccountAuthDetails.getPassword(), authentication, adminAccountAuthDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return SmsAdminAuthenticationToken.class.isAssignableFrom(authentication);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(iVerifyCodeService, "IVerifyCodeService must not be null");
		Assert.notNull(adminAccountAuthQryService, "AdminUserQrySvc must not be null");
	}

}
