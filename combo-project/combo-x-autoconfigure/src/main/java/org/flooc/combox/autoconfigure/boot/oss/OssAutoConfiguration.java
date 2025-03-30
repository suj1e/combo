package org.flooc.combox.autoconfigure.boot.oss;

import java.util.List;
import org.flooc.combox.boot.oss.IOssService;
import org.flooc.combox.boot.oss.OssConfiguration;
import org.flooc.combox.boot.oss.OssServiceType;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.plugin.core.SimplePluginRegistry;

/**
 * @author sujie
 * @since 1.0.0
 */
@AutoConfiguration
public class OssAutoConfiguration {

	@Import(OssConfiguration.class)
	static class EnableOssConfiguration {


		@Bean(IOssService.PLUGIN_BEAN_NAME)
		public PluginRegistry<IOssService, OssServiceType> ossServicePlugin(List<IOssService> ossServices) {
			return SimplePluginRegistry.of(ossServices);
		}

	}

}
