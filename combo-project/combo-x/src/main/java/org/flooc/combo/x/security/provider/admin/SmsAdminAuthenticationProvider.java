package org.flooc.combo.x.security.provider.admin;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flooc.combo.common.exception.CmpException;
import org.flooc.combo.x.constant.CmpExceptionConstant.SecurityExceptionEnum;
import org.flooc.combo.x.security.support.account.admin.AdminAccountAuthDetails;
import org.flooc.combo.x.security.support.svc.account.admin.AdminAccountAuthCmdSvc;
import org.flooc.combo.x.security.support.svc.account.admin.AdminAccountAuthQrySvc;
import org.flooc.combo.x.sms.check.SmsCodeCheckResult;
import org.flooc.combo.x.sms.check.SmsVerifyCodeChecker;
import org.flooc.combo.x.sms.check.VerifyCodeCheckModel;
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

	private final SmsVerifyCodeChecker smsVerifyCodeChecker;

	private final AdminAccountAuthQrySvc adminAccountAuthQrySvc;

	private final AdminAccountAuthCmdSvc adminAccountAuthCmdSvc;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		SmsAdminAuthenticationToken unauthenticatedToken = (SmsAdminAuthenticationToken) authentication;
		VerifyCodeCheckModel dto = new VerifyCodeCheckModel();
		String phone = unauthenticatedToken.getPhone();
		dto.setPhone(phone);
		dto.setVerifyCode(unauthenticatedToken.getVerifyCode());
		SmsCodeCheckResult check = smsVerifyCodeChecker.check(dto);
		if (!check.isValid()) {
			throw new CmpException(SecurityExceptionEnum.VerifyCodeIncorrect);
		}
		Optional<AdminAccountAuthDetails> authAccountOptional = adminAccountAuthQrySvc.queryAccount(phone);
		AdminAccountAuthDetails authAccountToUse;
		if (authAccountOptional.isEmpty()) {
			if (log.isDebugEnabled()) {
				log.debug("无此用户，验证码方式自动注册普通用户");
			}
			authAccountToUse = adminAccountAuthCmdSvc.registerNormalAccountByPhone(phone);
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
		Assert.notNull(smsVerifyCodeChecker, "SmsVerifyCodeChecker must not be null");
		Assert.notNull(adminAccountAuthQrySvc, "AdminUserQrySvc must not be null");
	}

}
