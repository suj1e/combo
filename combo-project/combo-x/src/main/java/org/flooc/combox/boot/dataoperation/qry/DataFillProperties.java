package org.flooc.combox.boot.dataoperation.qry;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.dataoperation.qry.fill")
@Getter
@Setter
public  class DataFillProperties {

		private String threadNamePrefix = "DataFillService-Task-";

		private boolean daemon = true;

		private int corePoolSize = 0;

		private int maxPoolSize = Runtime.getRuntime().availableProcessors() * 3;

		private int keepAliveSeconds = 60;

		private int queueCapacity = 1000;

	}