package org.springframework.data.rest.webmvc.halexplorer;

import org.springframework.aot.beans.factory.BeanDefinitionRegistrar;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public final class DemoApplicationTestsContextInitializer {
  public static void registerHalExplorerConfiguration(DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("org.springframework.data.rest.webmvc.halexplorer.HalExplorerConfiguration", HalExplorerConfiguration.class)
        .instanceSupplier(HalExplorerConfiguration::new).register(beanFactory);
  }

  public static void registerHalExplorerConfiguration_halExplorer(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("halExplorer", HalExplorer.class).withFactoryMethod(HalExplorerConfiguration.class, "halExplorer")
        .instanceSupplier(() -> beanFactory.getBean(HalExplorerConfiguration.class).halExplorer()).register(beanFactory);
  }
}
