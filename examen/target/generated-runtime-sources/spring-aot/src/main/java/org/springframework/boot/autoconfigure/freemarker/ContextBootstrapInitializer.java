package org.springframework.boot.autoconfigure.freemarker;

import freemarker.template.Configuration;
import org.springframework.aot.beans.factory.BeanDefinitionRegistrar;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

public final class ContextBootstrapInitializer {
  public static void registerFreeMarkerServletWebConfiguration(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.freemarker.FreeMarkerServletWebConfiguration", FreeMarkerServletWebConfiguration.class).withConstructor(FreeMarkerProperties.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> new FreeMarkerServletWebConfiguration(attributes.get(0)))).register(beanFactory);
  }

  public static void registerFreeMarkerServletWebConfiguration_freeMarkerConfigurer(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("freeMarkerConfigurer", FreeMarkerConfigurer.class).withFactoryMethod(FreeMarkerServletWebConfiguration.class, "freeMarkerConfigurer")
        .instanceSupplier(() -> beanFactory.getBean(FreeMarkerServletWebConfiguration.class).freeMarkerConfigurer()).register(beanFactory);
  }

  public static void registerFreeMarkerServletWebConfiguration_freeMarkerConfiguration(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("freeMarkerConfiguration", Configuration.class).withFactoryMethod(FreeMarkerServletWebConfiguration.class, "freeMarkerConfiguration", FreeMarkerConfig.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(FreeMarkerServletWebConfiguration.class).freeMarkerConfiguration(attributes.get(0)))).register(beanFactory);
  }

  public static void registerFreeMarkerServletWebConfiguration_freeMarkerViewResolver(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("freeMarkerViewResolver", FreeMarkerViewResolver.class).withFactoryMethod(FreeMarkerServletWebConfiguration.class, "freeMarkerViewResolver")
        .instanceSupplier(() -> beanFactory.getBean(FreeMarkerServletWebConfiguration.class).freeMarkerViewResolver()).register(beanFactory);
  }
}
