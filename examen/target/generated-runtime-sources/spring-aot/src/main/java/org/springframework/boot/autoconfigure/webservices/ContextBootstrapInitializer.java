package org.springframework.boot.autoconfigure.webservices;

import org.springframework.aot.beans.factory.BeanDefinitionRegistrar;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public final class ContextBootstrapInitializer {
  public static void registerWebServicesAutoConfiguration_WsConfiguration(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration$WsConfiguration", WebServicesAutoConfiguration.WsConfiguration.class)
        .instanceSupplier(WebServicesAutoConfiguration.WsConfiguration::new).register(beanFactory);
  }
}
