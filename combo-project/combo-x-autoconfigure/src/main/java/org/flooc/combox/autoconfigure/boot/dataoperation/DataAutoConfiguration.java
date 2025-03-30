package org.flooc.combox.autoconfigure.boot.dataoperation;

import java.util.List;
import org.apache.seata.common.util.IdWorker;
import org.flooc.combo.dataoperation.enums.DataState;
import org.flooc.combo.dataoperation.support.DataStateMapper;
import org.flooc.combo.dataoperation.validate.ValidateConfiguration;
import org.flooc.combo.serialnumber.SeataSerialNumberServiceImpl;
import org.flooc.combo.serialnumber.SerialNumberService;
import org.flooc.combo.serialnumber.SerialNumberType;
import org.flooc.combox.boot.dataoperation.config.DataOperationProperties;
import org.flooc.combox.boot.dataoperation.jpa.DefaultJpaAuditorAware;
import org.flooc.combox.boot.dataoperation.qry.DataQueryConfiguration;
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
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.plugin.core.SimplePluginRegistry;

/**
 * @author sujie
 * @since 1.0.0
 */
@AutoConfiguration
public class DataAutoConfiguration {

  @Configuration(proxyBeanMethods = false)
  @ConditionalOnClass(DataState.class)
  @Import({ValidateConfiguration.class, DataQueryConfiguration.class})
  static class EnableDataOperationConfig {

    @Bean
    @ConditionalOnMissingBean(DataStateMapper.class)
    public DataStateMapper dataStateMapper() {
      return new DataStateMapper();
    }

    @Bean(name = SerialNumberService.PLUGIN_BEAN_NAME)
    public PluginRegistry<SerialNumberService, SerialNumberType> serialNumberPluginRegistry(
        List<SerialNumberService> serialNumberServices) {
      return SimplePluginRegistry.of(serialNumberServices);
    }

    @ConditionalOnClass(IdWorker.class)
    static class SeataSerialNubmerAutoConfig {

      @Bean
      public SerialNumberService seataSerialNumberService() {
        return new SeataSerialNumberServiceImpl();
      }
    }


  }


  @Configuration(proxyBeanMethods = false)
  @ConditionalOnClass(JpaRepository.class)
  @EnableConfigurationProperties(DataOperationProperties.class)
  static class EnableJpaConfiguration {

    @Bean
    @ConditionalOnMissingBean(AuditorAware.class)
    @ConditionalOnMissingClass({
        "org.springframework.security.config.annotation.web.builders.HttpSecurity"})
    public DefaultJpaAuditorAware defaultJpaAuditorAware(
        DataOperationProperties dataOperationProperties) {
      return new DefaultJpaAuditorAware(dataOperationProperties.getDefaultAuditor());
    }

    @Bean
    public IdWorker idWorker(DataOperationProperties dataOperationProperties) {
      // workerId 可以为空，为空则默认自动生成
      return new IdWorker(dataOperationProperties.getWorkerId());
    }

  }


}
