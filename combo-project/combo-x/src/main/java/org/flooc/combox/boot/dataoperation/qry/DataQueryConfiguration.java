package org.flooc.combox.boot.dataoperation.qry;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import org.flooc.combo.dataoperation.query.filler.DataFillService;
import org.flooc.combo.dataoperation.query.filler.impl.DefaultDataFillService;
import org.flooc.combo.dataoperation.query.filler.process.element.factory.DataFillElementProcessorFactory;
import org.flooc.combo.dataoperation.query.filler.process.element.factory.DefaultDataFillElementProcessorFactory;
import org.flooc.combo.dataoperation.query.filler.process.group.factory.DataFillGroupProcessorFactory;
import org.flooc.combo.dataoperation.query.filler.process.group.factory.DefaultDataFillGroupProcessorFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author sujie
 * @since 1.0.0
 */
@Configuration(proxyBeanMethods = false)
public class DataQueryConfiguration {

	@Configuration(proxyBeanMethods = false)
	@EnableConfigurationProperties({ DataFillProperties.class })
	static class DataFillConfiguration {

		@Bean
		@ConditionalOnMissingBean
		public DataFillElementProcessorFactory dataFillElementProcessorFactory(ApplicationContext applicationContext) {
			return new DefaultDataFillElementProcessorFactory(new BeanFactoryResolver(applicationContext));
		}

		@Bean
		public Executor defaultDataFillExecutor(DataFillProperties dataFillProperties) {
			ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
			executor.setThreadNamePrefix(dataFillProperties.getThreadNamePrefix());
			executor.setDaemon(dataFillProperties.isDaemon());
			executor.setCorePoolSize(dataFillProperties.getCorePoolSize());
			executor.setMaxPoolSize(dataFillProperties.getMaxPoolSize());
			executor.setKeepAliveSeconds(dataFillProperties.getKeepAliveSeconds());
			executor.setRejectedExecutionHandler(new CallerRunsPolicy());
			executor.setQueueCapacity(dataFillProperties.getQueueCapacity());
			executor.initialize();
			return executor;
		}

		@Bean
		@ConditionalOnMissingBean
		public DataFillGroupProcessorFactory dataFillGroupInvokerFactory(
				Collection<? extends DataFillElementProcessorFactory> dataFillElementProcessorFactories,
				Map<String, Executor> executorMap) {
			return new DefaultDataFillGroupProcessorFactory(dataFillElementProcessorFactories, executorMap);
		}

		@Bean
		@ConditionalOnMissingBean
		public DataFillService dataFillService(DataFillGroupProcessorFactory groupProcessorFactory) {
			return new DefaultDataFillService(groupProcessorFactory);
		}

	}

}
