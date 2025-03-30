package org.flooc.combox.boot.web.startup;

import java.time.Duration;
import java.util.Collection;
import org.flooc.combo.common.spi.AppServiceLoader;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author sujie
 * @since 1.0.0
 */
public class SpringAppRunListener implements SpringApplicationRunListener {

  private final Collection<AppRunListener> appRunListeners = AppServiceLoader.load(AppRunListener.class);

  @Override
  public void starting(ConfigurableBootstrapContext bootstrapContext) {
    appRunListeners.forEach(AppRunListener::starting);
  }

  @Override
  public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext,
      ConfigurableEnvironment environment) {
    appRunListeners.forEach(appRunListener -> appRunListener.environmentPrepared(environment));
  }

  @Override
  public void contextPrepared(ConfigurableApplicationContext context) {
    appRunListeners.forEach(appRunListener -> appRunListener.contextPrepared(context));
  }

  @Override
  public void contextLoaded(ConfigurableApplicationContext context) {
    appRunListeners.forEach(appRunListener -> appRunListener.contextLoaded(context));
  }

  @Override
  public void started(ConfigurableApplicationContext context, Duration timeTaken) {
    appRunListeners.forEach(appRunListener -> appRunListener.started(context));
  }

  @Override
  public void failed(ConfigurableApplicationContext context, Throwable exception) {
    appRunListeners.forEach(appRunListener -> appRunListener.failed(context, exception));
  }

}
