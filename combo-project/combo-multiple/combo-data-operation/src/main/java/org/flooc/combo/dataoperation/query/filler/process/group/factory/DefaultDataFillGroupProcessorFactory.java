package org.flooc.combo.dataoperation.query.filler.process.group.factory;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import lombok.extern.slf4j.Slf4j;
import one.util.streamex.StreamEx;
import org.flooc.combo.common.enums.ExecutorType;
import org.flooc.combo.dataoperation.query.filler.DataFillConfig;
import org.flooc.combo.dataoperation.query.filler.process.element.factory.DataFillElementProcessorFactory;
import org.flooc.combo.dataoperation.query.filler.process.element.processor.DataFillElementProcessor;
import org.flooc.combo.dataoperation.query.filler.process.group.processor.ConcurrentDataFillGroupProcessor;
import org.flooc.combo.dataoperation.query.filler.process.group.processor.DataFillGroupProcessor;
import org.flooc.combo.dataoperation.query.filler.process.group.processor.OrderlyDataFillGroupProcessor;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.util.Assert;

/**
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
public class DefaultDataFillGroupProcessorFactory implements DataFillGroupProcessorFactory {

  private final List<DataFillElementProcessorFactory> dataFillElementProcessorFactories;

  private final Map<String, Executor> executorMap;

  public DefaultDataFillGroupProcessorFactory(
      Collection<? extends DataFillElementProcessorFactory> dataFillElementProcessorFactories,
      Map<String, Executor> executorMap) {
    this.dataFillElementProcessorFactories = List.copyOf(dataFillElementProcessorFactories);
    AnnotationAwareOrderComparator.sort(this.dataFillElementProcessorFactories);
    this.executorMap = executorMap;
  }

  @Override
  public DataFillGroupProcessor createProcessor(Class<?> config) {
    List<DataFillElementProcessor> processors = StreamEx.of(this.dataFillElementProcessorFactories)
        .flatMap(factory -> factory.createProcessor(config).stream())
        .toList();
    // 读取配置
    DataFillConfig dataFillConfig = config.getAnnotation(DataFillConfig.class);
    return buildDataFillGroupProcessor(config, dataFillConfig, processors);
  }

  private DataFillGroupProcessor buildDataFillGroupProcessor(Class<?> config,
      DataFillConfig dataFillConfig,
      List<DataFillElementProcessor> processors) {
    // 有序
    if (dataFillConfig == null || dataFillConfig.processType() == ExecutorType.ORDERLY) {
      if (log.isDebugEnabled()) {
        log.debug("DataFill user orderly processor for {}", config);
      }
      return new OrderlyDataFillGroupProcessor(config, processors);
    }
    Assert.state(dataFillConfig.processType() == ExecutorType.CONCURRENT,
        "DataFill config illegal for " + config);
    // 并发
    if (log.isDebugEnabled()) {
      log.debug("DataFill user concurrent processor for {}", config);
    }
    Executor executor = executorMap.get(dataFillConfig.executorName());
    Assert.state(executor != null, "DataFill executor not found for " + config);
    return new ConcurrentDataFillGroupProcessor(config, processors, executor);
  }

}
