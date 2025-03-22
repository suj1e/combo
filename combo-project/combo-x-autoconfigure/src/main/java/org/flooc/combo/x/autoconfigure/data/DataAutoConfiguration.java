package org.flooc.combo.x.autoconfigure.data;

import org.apache.seata.common.util.IdWorker;
import org.flooc.combo.x.data.common.config.DataCommonProperties;
import org.flooc.combo.x.data.jpa.DefaultJpaAuditorAware;
import org.flooc.combo.x.data.jpa.ValidStatusMapper;
import org.flooc.combo.x.data.qry.DataQryConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sujie
 * @since 1.0.0
 */
@AutoConfiguration
public class DataAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean(ValidStatusMapper.class)
	public ValidStatusMapper validStatusMapper() {
		return new ValidStatusMapper();
	}

	@Configuration(proxyBeanMethods = false)
	@ConditionalOnClass(JpaRepository.class)
	@EnableConfigurationProperties(DataCommonProperties.class)
	static class EnableJpaConfiguration {

		@Bean
		@ConditionalOnMissingBean(AuditorAware.class)
		@ConditionalOnMissingClass({ "org.springframework.security.config.annotation.web.builders.HttpSecurity" })
		public DefaultJpaAuditorAware defaultJpaAuditorAware() {
			return new DefaultJpaAuditorAware();
		}

		@Bean
		public IdWorker idWorker(DataCommonProperties dataCommonProperties) {
			// workerId 可以为空，为空则默认自动生成
			return new IdWorker(dataCommonProperties.getWorkerId());
		}

	}

	@Import({ DataQryConfiguration.class })
	static class EnableQryConfiguration {

	}

}
