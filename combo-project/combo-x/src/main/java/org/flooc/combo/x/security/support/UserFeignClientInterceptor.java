package org.flooc.combo.x.security.support;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.flooc.combo.x.security.util.SecurityUtils;

public class UserFeignClientInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER = "Bearer";

    @Override
    public void apply(RequestTemplate template) {
        SecurityUtils.getCurrentUserJWT().ifPresent(s -> template.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER, s)));
    }

}
