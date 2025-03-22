package org.flooc.combo.x.web.startup;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.flooc.combo.common.exception.CmpException;
import org.flooc.combo.x.constant.CmpExceptionConstant.StartupExceptionEnum;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
public class StartingAppRunListener implements AppRunListener {

  private static final String APP_STARTUP_IP = "app.startup.ip";
  private static final String COMBO_VERSION = "combo.version";

  private volatile boolean starting;

  @Override
  public void starting() {
    starting = true;
  }

  @Override
  public void environmentPrepared(ConfigurableEnvironment environment) {
    setSystemProperty();
    if (starting) {
      log.info("App {} is starting...", environment.getProperty("spring.application.name"));
    }
  }

  @Override
  public void contextPrepared(ConfigurableApplicationContext context) {
    SpringAppUtil.setApplicationContext(context);
    if (log.isDebugEnabled()) {
      log.debug("SpringAppUtil has bean initialized");
    }
  }

  @Override
  public void started(ConfigurableApplicationContext context) {
    starting = false;
  }

  @Override
  public void failed(ConfigurableApplicationContext context, Throwable exception) {
    starting = false;

    log.error("Failed to startup app {}",
        context.getEnvironment().getProperty("spring.application.name"), exception);

    context.close();
  }

  private void setSystemProperty() {
    String appStartupIp = loadAppStartupIp();
    String comboVersion = String.format("v%s", loadComboVersion());
    System.setProperty(APP_STARTUP_IP, appStartupIp);
    System.setProperty(COMBO_VERSION, comboVersion);
  }

  private String loadComboVersion() {
    try {
      Properties properties = PropertiesLoaderUtils.loadAllProperties("version.properties");
      return properties.getProperty(COMBO_VERSION);
    } catch (IOException exception) {
      throw new CmpException(StartupExceptionEnum.LoadComboVersionError);
    }
  }

  private String loadAppStartupIp() {
    try {
      return InetAddress.getLocalHost().getHostAddress();
    } catch (UnknownHostException e) {
      throw new CmpException(StartupExceptionEnum.NotFindAppStartupIp);
    }
  }
}
