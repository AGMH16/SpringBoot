package com.vaadin.flow.spring;

import java.lang.reflect.Field;
import org.springframework.aot.beans.factory.BeanDefinitionRegistrar;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.WebApplicationContext;

public final class ContextBootstrapInitializer {
  public static void registerSpringBootAutoConfiguration(DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("com.vaadin.flow.spring.SpringBootAutoConfiguration", SpringBootAutoConfiguration.class)
        .instanceSupplier((instanceContext) -> {
          SpringBootAutoConfiguration bean = new SpringBootAutoConfiguration();
          instanceContext.field("context", WebApplicationContext.class)
              .invoke(beanFactory, (attributes) -> {
                Field contextField = ReflectionUtils.findField(SpringBootAutoConfiguration.class, "context", WebApplicationContext.class);
                ReflectionUtils.makeAccessible(contextField);
                ReflectionUtils.setField(contextField, bean, attributes.get(0));
              });
                  return bean;
                }).register(beanFactory);
          }
        }
