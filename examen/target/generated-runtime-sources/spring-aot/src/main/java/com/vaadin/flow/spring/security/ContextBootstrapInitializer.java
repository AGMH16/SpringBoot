package com.vaadin.flow.spring.security;

import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.server.auth.ViewAccessChecker;
import com.vaadin.flow.spring.SpringSecurityAutoConfiguration;
import com.vaadin.flow.spring.VaadinConfigurationProperties;
import java.lang.String;
import java.lang.reflect.Field;
import org.springframework.aot.beans.factory.BeanDefinitionRegistrar;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ReflectionUtils;

public final class ContextBootstrapInitializer {
  public static void registerSpringSecurityAutoConfiguration_vaadinDefaultRequestCache(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("vaadinDefaultRequestCache", VaadinDefaultRequestCache.class).withFactoryMethod(SpringSecurityAutoConfiguration.class, "vaadinDefaultRequestCache")
        .instanceSupplier((instanceContext) -> {
          VaadinDefaultRequestCache bean = beanFactory.getBean(SpringSecurityAutoConfiguration.class).vaadinDefaultRequestCache();
          instanceContext.field("requestUtil", RequestUtil.class)
              .invoke(beanFactory, (attributes) -> {
                Field requestUtilField = ReflectionUtils.findField(VaadinDefaultRequestCache.class, "requestUtil", RequestUtil.class);
                ReflectionUtils.makeAccessible(requestUtilField);
                ReflectionUtils.setField(requestUtilField, bean, attributes.get(0));
              });
                  instanceContext.field("configuredErrorPath", String.class)
                      .invoke(beanFactory, (attributes) -> {
                        Field configuredErrorPathField = ReflectionUtils.findField(VaadinDefaultRequestCache.class, "configuredErrorPath", String.class);
                        ReflectionUtils.makeAccessible(configuredErrorPathField);
                        ReflectionUtils.setField(configuredErrorPathField, bean, attributes.get(0));
                      });
                          return bean;
                        }).register(beanFactory);
                  }

                  public static void registerSpringSecurityAutoConfiguration_viewAccessCheckerInitializer(
                      DefaultListableBeanFactory beanFactory) {
                    BeanDefinitionRegistrar.of("viewAccessCheckerInitializer", ViewAccessCheckerInitializer.class).withFactoryMethod(SpringSecurityAutoConfiguration.class, "viewAccessCheckerInitializer")
                        .instanceSupplier((instanceContext) -> {
                          ViewAccessCheckerInitializer bean = beanFactory.getBean(SpringSecurityAutoConfiguration.class).viewAccessCheckerInitializer();
                          instanceContext.field("viewAccessChecker", ViewAccessChecker.class)
                              .invoke(beanFactory, (attributes) -> {
                                Field viewAccessCheckerField = ReflectionUtils.findField(ViewAccessCheckerInitializer.class, "viewAccessChecker", ViewAccessChecker.class);
                                ReflectionUtils.makeAccessible(viewAccessCheckerField);
                                ReflectionUtils.setField(viewAccessCheckerField, bean, attributes.get(0));
                              });
                                  return bean;
                                }).register(beanFactory);
                          }

                          public static void registerSpringSecurityAutoConfiguration_requestUtil(
                              DefaultListableBeanFactory beanFactory) {
                            BeanDefinitionRegistrar.of("requestUtil", RequestUtil.class).withFactoryMethod(SpringSecurityAutoConfiguration.class, "requestUtil")
                                .instanceSupplier((instanceContext) -> {
                                  RequestUtil bean = beanFactory.getBean(SpringSecurityAutoConfiguration.class).requestUtil();
                                  instanceContext.field("applicationContext", ApplicationContext.class)
                                      .invoke(beanFactory, (attributes) -> {
                                        Field applicationContextField = ReflectionUtils.findField(RequestUtil.class, "applicationContext", ApplicationContext.class);
                                        ReflectionUtils.makeAccessible(applicationContextField);
                                        ReflectionUtils.setField(applicationContextField, bean, attributes.get(0));
                                      });
                                          instanceContext.field("accessAnnotationChecker", AccessAnnotationChecker.class)
                                              .invoke(beanFactory, (attributes) -> {
                                                Field accessAnnotationCheckerField = ReflectionUtils.findField(RequestUtil.class, "accessAnnotationChecker", AccessAnnotationChecker.class);
                                                ReflectionUtils.makeAccessible(accessAnnotationCheckerField);
                                                ReflectionUtils.setField(accessAnnotationCheckerField, bean, attributes.get(0));
                                              });
                                                  instanceContext.field("configurationProperties", VaadinConfigurationProperties.class)
                                                      .invoke(beanFactory, (attributes) -> {
                                                        Field configurationPropertiesField = ReflectionUtils.findField(RequestUtil.class, "configurationProperties", VaadinConfigurationProperties.class);
                                                        ReflectionUtils.makeAccessible(configurationPropertiesField);
                                                        ReflectionUtils.setField(configurationPropertiesField, bean, attributes.get(0));
                                                      });
                                                          instanceContext.field("springServletRegistration", ServletRegistrationBean.class)
                                                              .invoke(beanFactory, (attributes) -> {
                                                                Field springServletRegistrationField = ReflectionUtils.findField(RequestUtil.class, "springServletRegistration", ServletRegistrationBean.class);
                                                                ReflectionUtils.makeAccessible(springServletRegistrationField);
                                                                ReflectionUtils.setField(springServletRegistrationField, bean, attributes.get(0));
                                                              });
                                                                  return bean;
                                                                }).register(beanFactory);
                                                          }
                                                        }
