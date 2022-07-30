package org.springframework.boot.autoconfigure.security.servlet;

import org.springframework.aot.beans.factory.BeanDefinitionRegistrar;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.filter.ErrorPageSecurityFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public final class DemoApplicationTestsContextInitializer {
  public static void registerSpringBootWebSecurityConfiguration_WebSecurityEnablerConfiguration(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.security.servlet.SpringBootWebSecurityConfiguration$WebSecurityEnablerConfiguration", SpringBootWebSecurityConfiguration.WebSecurityEnablerConfiguration.class)
        .instanceSupplier(SpringBootWebSecurityConfiguration.WebSecurityEnablerConfiguration::new).register(beanFactory);
  }

  public static void registerSpringBootWebSecurityConfiguration_ErrorPageSecurityFilterConfiguration(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.security.servlet.SpringBootWebSecurityConfiguration$ErrorPageSecurityFilterConfiguration", SpringBootWebSecurityConfiguration.ErrorPageSecurityFilterConfiguration.class)
        .instanceSupplier(SpringBootWebSecurityConfiguration.ErrorPageSecurityFilterConfiguration::new).register(beanFactory);
  }

  public static void registerErrorPageSecurityFilterConfiguration_errorPageSecurityFilter(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("errorPageSecurityFilter", ResolvableType.forClassWithGenerics(FilterRegistrationBean.class, ErrorPageSecurityFilter.class)).withFactoryMethod(SpringBootWebSecurityConfiguration.ErrorPageSecurityFilterConfiguration.class, "errorPageSecurityFilter", ApplicationContext.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(SpringBootWebSecurityConfiguration.ErrorPageSecurityFilterConfiguration.class).errorPageSecurityFilter(attributes.get(0)))).register(beanFactory);
  }

  public static void registerSpringBootWebSecurityConfiguration_SecurityFilterChainConfiguration(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.security.servlet.SpringBootWebSecurityConfiguration$SecurityFilterChainConfiguration", SpringBootWebSecurityConfiguration.SecurityFilterChainConfiguration.class)
        .instanceSupplier(SpringBootWebSecurityConfiguration.SecurityFilterChainConfiguration::new).register(beanFactory);
  }

  public static void registerSecurityFilterChainConfiguration_defaultSecurityFilterChain(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("defaultSecurityFilterChain", SecurityFilterChain.class).withFactoryMethod(SpringBootWebSecurityConfiguration.SecurityFilterChainConfiguration.class, "defaultSecurityFilterChain", HttpSecurity.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(SpringBootWebSecurityConfiguration.SecurityFilterChainConfiguration.class).defaultSecurityFilterChain(attributes.get(0)))).register(beanFactory);
  }

  public static void registerSpringBootWebSecurityConfiguration(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.security.servlet.SpringBootWebSecurityConfiguration", SpringBootWebSecurityConfiguration.class)
        .instanceSupplier(SpringBootWebSecurityConfiguration::new).register(beanFactory);
  }
}
