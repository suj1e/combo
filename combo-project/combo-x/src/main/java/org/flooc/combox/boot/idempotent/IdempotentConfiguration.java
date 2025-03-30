package org.flooc.combox.boot.idempotent;

import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sujie
 * @since 1.0.0
 */
@Configuration(proxyBeanMethods = false)
public class IdempotentConfiguration {

	@Bean
	public DefaultPointcutAdvisor idempotentAdvisor() {
		return IdempotentAdvisorFactory.buildAdvisor();
	}

}
