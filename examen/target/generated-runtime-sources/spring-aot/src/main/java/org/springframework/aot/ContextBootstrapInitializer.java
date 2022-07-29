package org.springframework.aot;

import com.vaadin.flow.server.startup.ApplicationConfigurationFactory;
import com.vaadin.flow.spring.SpringBootAutoConfiguration;
import com.vaadin.flow.spring.SpringServlet;
import com.vaadin.flow.spring.VaadinApplicationConfiguration;
import com.vaadin.flow.spring.VaadinConfigurationProperties;
import com.vaadin.flow.spring.VaadinScopesConfig;
import com.vaadin.flow.spring.VaadinServletConfiguration;
import examen.demo.DemoApplication;
import java.lang.Object;
import java.lang.Override;
import java.util.List;
import javax.servlet.MultipartConfigElement;
import javax.validation.Validator;
import org.springframework.aot.beans.factory.BeanDefinitionRegistrar;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.LazyInitializationExcludeFilter;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.autoconfigure.availability.ApplicationAvailabilityAutoConfiguration;
import org.springframework.boot.autoconfigure.codec.CodecProperties;
import org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration;
import org.springframework.boot.autoconfigure.context.LifecycleAutoConfiguration;
import org.springframework.boot.autoconfigure.context.LifecycleProperties;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestProperties;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
import org.springframework.boot.autoconfigure.hateoas.HateoasProperties;
import org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.autoconfigure.http.codec.CodecsAutoConfiguration;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.boot.autoconfigure.netty.NettyAutoConfiguration;
import org.springframework.boot.autoconfigure.netty.NettyProperties;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationProperties;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskSchedulingProperties;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.boot.autoconfigure.transaction.TransactionProperties;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.web.client.RestTemplateBuilderConfigurer;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.embedded.NettyWebServerFactoryCustomizer;
import org.springframework.boot.autoconfigure.web.embedded.TomcatWebServerFactoryCustomizer;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties;
import org.springframework.boot.autoconfigure.web.reactive.function.client.ClientHttpConnectorAutoConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryCustomizer;
import org.springframework.boot.autoconfigure.web.servlet.TomcatServletWebServerFactoryCustomizer;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration;
import org.springframework.boot.autoconfigure.webservices.WebServicesProperties;
import org.springframework.boot.autoconfigure.webservices.client.WebServiceTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketMessagingAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;
import org.springframework.boot.availability.ApplicationAvailabilityBean;
import org.springframework.boot.context.properties.BoundConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.boot.jackson.JsonComponentModule;
import org.springframework.boot.jackson.JsonMixinModule;
import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.boot.task.TaskSchedulerBuilder;
import org.springframework.boot.validation.beanvalidation.MethodValidationExcludeFilter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.boot.web.server.ErrorPageRegistrarBeanPostProcessor;
import org.springframework.boot.web.server.WebServerFactoryCustomizerBeanPostProcessor;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.filter.OrderedFormContentFilter;
import org.springframework.boot.webservices.client.WebServiceTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.support.DefaultLifecycleProcessor;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.auditing.AuditableBeanWrapperFactory;
import org.springframework.data.geo.GeoModule;
import org.springframework.data.mapping.context.PersistentEntities;
import org.springframework.data.repository.support.Repositories;
import org.springframework.data.repository.support.RepositoryInvokerFactory;
import org.springframework.data.rest.core.config.MetadataConfiguration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.AnnotatedEventHandlerInvoker;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.core.mapping.RepositoryResourceMappings;
import org.springframework.data.rest.core.support.RepositoryRelProvider;
import org.springframework.data.rest.core.support.SelfLinkProvider;
import org.springframework.data.rest.webmvc.BaseUri;
import org.springframework.data.rest.webmvc.HttpHeadersPreparer;
import org.springframework.data.rest.webmvc.ProfileResourceProcessor;
import org.springframework.data.rest.webmvc.RepositoryRestExceptionHandler;
import org.springframework.data.rest.webmvc.ServerHttpRequestMethodArgumentResolver;
import org.springframework.data.rest.webmvc.alps.AlpsJsonHttpMessageConverter;
import org.springframework.data.rest.webmvc.alps.RootResourceInformationToAlpsDescriptorConverter;
import org.springframework.data.rest.webmvc.config.PersistentEntityResourceHandlerMethodArgumentResolver;
import org.springframework.data.rest.webmvc.config.ProjectionDefinitionRegistar;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.data.rest.webmvc.config.ResourceMetadataHandlerMethodArgumentResolver;
import org.springframework.data.rest.webmvc.config.RootResourceInformationHandlerMethodArgumentResolver;
import org.springframework.data.rest.webmvc.convert.UriListHttpMessageConverter;
import org.springframework.data.rest.webmvc.json.EnumTranslator;
import org.springframework.data.rest.webmvc.json.PersistentEntityToJsonSchemaConverter;
import org.springframework.data.rest.webmvc.mapping.Associations;
import org.springframework.data.rest.webmvc.mapping.LinkCollector;
import org.springframework.data.rest.webmvc.support.BackendIdHandlerMethodArgumentResolver;
import org.springframework.data.rest.webmvc.support.ETagArgumentResolver;
import org.springframework.data.rest.webmvc.support.ExcerptProjector;
import org.springframework.data.rest.webmvc.support.JpaHelper;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.HateoasSortHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.PagedResourcesAssemblerArgumentResolver;
import org.springframework.data.web.config.HateoasAwareSpringDataWebConfiguration;
import org.springframework.data.web.config.SpringDataJacksonConfiguration;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.config.HateoasConfiguration;
import org.springframework.hateoas.mediatype.MessageResolver;
import org.springframework.hateoas.mediatype.hal.HalMediaTypeConfiguration;
import org.springframework.hateoas.server.LinkRelationProvider;
import org.springframework.hateoas.server.mvc.TypeConstrainedMappingJackson2HttpMessageConverter;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.plugin.core.OrderAwarePluginRegistry;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.plugin.core.support.PluginRegistryFactoryBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.PathMatcher;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.method.support.CompositeUriComponentsContributor;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.RequestToViewNameTranslator;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.function.support.HandlerFunctionAdapter;
import org.springframework.web.servlet.function.support.RouterFunctionMapping;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;
import org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.springframework.web.util.UrlPathHelper;
import org.springframework.web.util.pattern.PathPatternParser;
import org.springframework.ws.config.annotation.DelegatingWsConfiguration;
import org.springframework.ws.config.annotation.WsConfigurationSupport;
import org.springframework.ws.server.endpoint.adapter.DefaultMethodEndpointAdapter;
import org.springframework.ws.server.endpoint.mapping.PayloadRootAnnotationMethodEndpointMapping;
import org.springframework.ws.soap.addressing.server.AnnotationActionEndpointMapping;
import org.springframework.ws.soap.server.endpoint.SimpleSoapExceptionResolver;
import org.springframework.ws.soap.server.endpoint.SoapFaultAnnotationExceptionResolver;
import org.springframework.ws.soap.server.endpoint.mapping.SoapActionAnnotationMethodEndpointMapping;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

public class ContextBootstrapInitializer implements ApplicationContextInitializer<GenericApplicationContext> {
  @Override
  public void initialize(GenericApplicationContext context) {
    // infrastructure
    DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();
    beanFactory.setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());

    BeanDefinitionRegistrar.of("examen.demo.DemoApplication", DemoApplication.class)
        .instanceSupplier(DemoApplication::new).register(beanFactory);
    org.springframework.boot.autoconfigure.ContextBootstrapInitializer.registerAutoConfigurationPackages_BasePackages(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration", PropertyPlaceholderAutoConfiguration.class)
        .instanceSupplier(PropertyPlaceholderAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("propertySourcesPlaceholderConfigurer", PropertySourcesPlaceholderConfigurer.class).withFactoryMethod(PropertyPlaceholderAutoConfiguration.class, "propertySourcesPlaceholderConfigurer")
        .instanceSupplier(() -> PropertyPlaceholderAutoConfiguration.propertySourcesPlaceholderConfigurer()).register(beanFactory);
    org.springframework.boot.autoconfigure.jackson.ContextBootstrapInitializer.registerJacksonAutoConfiguration_Jackson2ObjectMapperBuilderCustomizerConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.jackson.ContextBootstrapInitializer.registerJackson2ObjectMapperBuilderCustomizerConfiguration_standardJacksonObjectMapperBuilderCustomizer(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor", ConfigurationPropertiesBindingPostProcessor.class)
        .instanceSupplier(ConfigurationPropertiesBindingPostProcessor::new).customize((bd) -> bd.setRole(2)).register(beanFactory);
    org.springframework.boot.context.properties.ContextBootstrapInitializer.registerConfigurationPropertiesBinder_Factory(beanFactory);
    org.springframework.boot.context.properties.ContextBootstrapInitializer.registerConfigurationPropertiesBinder(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.context.properties.BoundConfigurationProperties", BoundConfigurationProperties.class)
        .instanceSupplier(BoundConfigurationProperties::new).customize((bd) -> bd.setRole(2)).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.context.properties.EnableConfigurationPropertiesRegistrar.methodValidationExcludeFilter", MethodValidationExcludeFilter.class)
        .instanceSupplier(() -> MethodValidationExcludeFilter.byAnnotation(ConfigurationProperties.class)).customize((bd) -> bd.setRole(2)).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.jackson-org.springframework.boot.autoconfigure.jackson.JacksonProperties", JacksonProperties.class)
        .instanceSupplier(JacksonProperties::new).register(beanFactory);
    org.springframework.boot.autoconfigure.jackson.ContextBootstrapInitializer.registerJacksonAutoConfiguration_JacksonObjectMapperBuilderConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.jackson.ContextBootstrapInitializer.registerJacksonObjectMapperBuilderConfiguration_jacksonObjectMapperBuilder(beanFactory);
    org.springframework.boot.autoconfigure.jackson.ContextBootstrapInitializer.registerJacksonAutoConfiguration_ParameterNamesModuleConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.jackson.ContextBootstrapInitializer.registerParameterNamesModuleConfiguration_parameterNamesModule(beanFactory);
    org.springframework.boot.autoconfigure.jackson.ContextBootstrapInitializer.registerJacksonAutoConfiguration_JacksonObjectMapperConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.jackson.ContextBootstrapInitializer.registerJacksonObjectMapperConfiguration_jacksonObjectMapper(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration", JacksonAutoConfiguration.class)
        .instanceSupplier(JacksonAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("jsonComponentModule", JsonComponentModule.class).withFactoryMethod(JacksonAutoConfiguration.class, "jsonComponentModule")
        .instanceSupplier(() -> beanFactory.getBean(JacksonAutoConfiguration.class).jsonComponentModule()).register(beanFactory);
    BeanDefinitionRegistrar.of("jsonMixinModule", JsonMixinModule.class).withFactoryMethod(JacksonAutoConfiguration.class, "jsonMixinModule", ApplicationContext.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(JacksonAutoConfiguration.class).jsonMixinModule(attributes.get(0)))).register(beanFactory);
    org.springframework.boot.autoconfigure.websocket.servlet.ContextBootstrapInitializer.registerWebSocketServletAutoConfiguration_TomcatWebSocketConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.websocket.servlet.ContextBootstrapInitializer.registerTomcatWebSocketConfiguration_websocketServletWebServerCustomizer(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration", WebSocketServletAutoConfiguration.class)
        .instanceSupplier(WebSocketServletAutoConfiguration::new).register(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.ContextBootstrapInitializer.registerServletWebServerFactoryConfiguration_EmbeddedTomcat(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.ContextBootstrapInitializer.registerEmbeddedTomcat_tomcatServletWebServerFactory(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration", ServletWebServerFactoryAutoConfiguration.class)
        .instanceSupplier(ServletWebServerFactoryAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("servletWebServerFactoryCustomizer", ServletWebServerFactoryCustomizer.class).withFactoryMethod(ServletWebServerFactoryAutoConfiguration.class, "servletWebServerFactoryCustomizer", ServerProperties.class, ObjectProvider.class, ObjectProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(ServletWebServerFactoryAutoConfiguration.class).servletWebServerFactoryCustomizer(attributes.get(0), attributes.get(1), attributes.get(2)))).register(beanFactory);
    BeanDefinitionRegistrar.of("tomcatServletWebServerFactoryCustomizer", TomcatServletWebServerFactoryCustomizer.class).withFactoryMethod(ServletWebServerFactoryAutoConfiguration.class, "tomcatServletWebServerFactoryCustomizer", ServerProperties.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(ServletWebServerFactoryAutoConfiguration.class).tomcatServletWebServerFactoryCustomizer(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("server-org.springframework.boot.autoconfigure.web.ServerProperties", ServerProperties.class)
        .instanceSupplier(ServerProperties::new).register(beanFactory);
    BeanDefinitionRegistrar.of("webServerFactoryCustomizerBeanPostProcessor", WebServerFactoryCustomizerBeanPostProcessor.class)
        .instanceSupplier(WebServerFactoryCustomizerBeanPostProcessor::new).customize((bd) -> bd.setSynthetic(true)).register(beanFactory);
    BeanDefinitionRegistrar.of("errorPageRegistrarBeanPostProcessor", ErrorPageRegistrarBeanPostProcessor.class)
        .instanceSupplier(ErrorPageRegistrarBeanPostProcessor::new).customize((bd) -> bd.setSynthetic(true)).register(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.ContextBootstrapInitializer.registerDispatcherServletAutoConfiguration_DispatcherServletConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.ContextBootstrapInitializer.registerDispatcherServletConfiguration_dispatcherServlet(beanFactory);
    BeanDefinitionRegistrar.of("spring.mvc-org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties", WebMvcProperties.class)
        .instanceSupplier(WebMvcProperties::new).register(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.ContextBootstrapInitializer.registerDispatcherServletAutoConfiguration_DispatcherServletRegistrationConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.ContextBootstrapInitializer.registerDispatcherServletRegistrationConfiguration_dispatcherServletRegistration(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration", DispatcherServletAutoConfiguration.class)
        .instanceSupplier(DispatcherServletAutoConfiguration::new).register(beanFactory);
    org.springframework.boot.autoconfigure.http.codec.ContextBootstrapInitializer.registerCodecsAutoConfiguration_DefaultCodecsConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.http.codec.ContextBootstrapInitializer.registerDefaultCodecsConfiguration_defaultCodecCustomizer(beanFactory);
    org.springframework.boot.autoconfigure.http.codec.ContextBootstrapInitializer.registerCodecsAutoConfiguration_JacksonCodecConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.http.codec.ContextBootstrapInitializer.registerJacksonCodecConfiguration_jacksonCodecCustomizer(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.http.codec.CodecsAutoConfiguration", CodecsAutoConfiguration.class)
        .instanceSupplier(CodecsAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.codec-org.springframework.boot.autoconfigure.codec.CodecProperties", CodecProperties.class)
        .instanceSupplier(CodecProperties::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration", ValidationAutoConfiguration.class)
        .instanceSupplier(ValidationAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("defaultValidator", LocalValidatorFactoryBean.class).withFactoryMethod(ValidationAutoConfiguration.class, "defaultValidator", ApplicationContext.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> ValidationAutoConfiguration.defaultValidator(attributes.get(0)))).customize((bd) -> {
      bd.setPrimary(true);
      bd.setRole(2);
    }).register(beanFactory);
    BeanDefinitionRegistrar.of("methodValidationPostProcessor", MethodValidationPostProcessor.class).withFactoryMethod(ValidationAutoConfiguration.class, "methodValidationPostProcessor", Environment.class, Validator.class, ObjectProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> ValidationAutoConfiguration.methodValidationPostProcessor(attributes.get(0), attributes.get(1), attributes.get(2)))).register(beanFactory);
    org.springframework.boot.autoconfigure.session.ContextBootstrapInitializer.registerSessionAutoConfiguration_ServletSessionRepositoryImplementationValidator(beanFactory);
    BeanDefinitionRegistrar.of("java.lang.Object", Object.class)
        .instanceSupplier(Object::new).register(beanFactory);
    org.springframework.boot.autoconfigure.session.ContextBootstrapInitializer.registerNoOpSessionConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.session.ContextBootstrapInitializer.registerServletSessionConfiguration_ServletSessionRepositoryConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.session.ContextBootstrapInitializer.registerSessionAutoConfiguration_ServletSessionRepositoryValidator(beanFactory);
    org.springframework.boot.autoconfigure.session.ContextBootstrapInitializer.registerSessionAutoConfiguration_ServletSessionConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.session.ContextBootstrapInitializer.registerServletSessionConfiguration_cookieSerializer(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.session.SessionAutoConfiguration", SessionAutoConfiguration.class)
        .instanceSupplier(SessionAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.webflux-org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties", WebFluxProperties.class)
        .instanceSupplier(WebFluxProperties::new).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.session-org.springframework.boot.autoconfigure.session.SessionProperties", SessionProperties.class)
        .instanceSupplier(SessionProperties::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration", TaskExecutionAutoConfiguration.class)
        .instanceSupplier(TaskExecutionAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("taskExecutorBuilder", TaskExecutorBuilder.class).withFactoryMethod(TaskExecutionAutoConfiguration.class, "taskExecutorBuilder", TaskExecutionProperties.class, ObjectProvider.class, ObjectProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(TaskExecutionAutoConfiguration.class).taskExecutorBuilder(attributes.get(0), attributes.get(1), attributes.get(2)))).register(beanFactory);
    BeanDefinitionRegistrar.of("applicationTaskExecutor", ThreadPoolTaskExecutor.class).withFactoryMethod(TaskExecutionAutoConfiguration.class, "applicationTaskExecutor", TaskExecutorBuilder.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(TaskExecutionAutoConfiguration.class).applicationTaskExecutor(attributes.get(0)))).customize((bd) -> bd.setLazyInit(true)).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.task.execution-org.springframework.boot.autoconfigure.task.TaskExecutionProperties", TaskExecutionProperties.class)
        .instanceSupplier(TaskExecutionProperties::new).register(beanFactory);
    BeanDefinitionRegistrar.of("com.vaadin.flow.spring.VaadinApplicationConfiguration", VaadinApplicationConfiguration.class)
        .instanceSupplier(VaadinApplicationConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("defaultApplicationConfigurationFactory", ApplicationConfigurationFactory.class).withFactoryMethod(VaadinApplicationConfiguration.class, "defaultApplicationConfigurationFactory")
        .instanceSupplier(() -> beanFactory.getBean(VaadinApplicationConfiguration.class).defaultApplicationConfigurationFactory()).register(beanFactory);
    BeanDefinitionRegistrar.of("vaadinApplicationContextInitializer", ApplicationContextAware.class).withFactoryMethod(VaadinApplicationConfiguration.class, "vaadinApplicationContextInitializer")
        .instanceSupplier(() -> beanFactory.getBean(VaadinApplicationConfiguration.class).vaadinApplicationContextInitializer()).register(beanFactory);
    BeanDefinitionRegistrar.of("com.vaadin.flow.spring.VaadinServletConfiguration", VaadinServletConfiguration.class)
        .instanceSupplier(VaadinServletConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("vaadinRootMapping", SimpleUrlHandlerMapping.class).withFactoryMethod(VaadinServletConfiguration.class, "vaadinRootMapping")
        .instanceSupplier(() -> beanFactory.getBean(VaadinServletConfiguration.class).vaadinRootMapping()).register(beanFactory);
    BeanDefinitionRegistrar.of("vaadinForwardingController", Controller.class).withFactoryMethod(VaadinServletConfiguration.class, "vaadinForwardingController")
        .instanceSupplier(() -> beanFactory.getBean(VaadinServletConfiguration.class).vaadinForwardingController()).register(beanFactory);
    com.vaadin.flow.spring.ContextBootstrapInitializer.registerSpringBootAutoConfiguration(beanFactory);
    BeanDefinitionRegistrar.of("contextInitializer", ServletContextInitializer.class).withFactoryMethod(SpringBootAutoConfiguration.class, "contextInitializer")
        .instanceSupplier(() -> beanFactory.getBean(SpringBootAutoConfiguration.class).contextInitializer()).register(beanFactory);
    BeanDefinitionRegistrar.of("servletRegistrationBean", ResolvableType.forClassWithGenerics(ServletRegistrationBean.class, SpringServlet.class)).withFactoryMethod(SpringBootAutoConfiguration.class, "servletRegistrationBean", ObjectProvider.class, VaadinConfigurationProperties.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(SpringBootAutoConfiguration.class).servletRegistrationBean(attributes.get(0), attributes.get(1)))).register(beanFactory);
    BeanDefinitionRegistrar.of("websocketEndpointDeployer", ServerEndpointExporter.class).withFactoryMethod(SpringBootAutoConfiguration.class, "websocketEndpointDeployer")
        .instanceSupplier(() -> beanFactory.getBean(SpringBootAutoConfiguration.class).websocketEndpointDeployer()).register(beanFactory);
    BeanDefinitionRegistrar.of("vaadin-com.vaadin.flow.spring.VaadinConfigurationProperties", VaadinConfigurationProperties.class)
        .instanceSupplier(VaadinConfigurationProperties::new).register(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.error.ContextBootstrapInitializer.registerErrorMvcAutoConfiguration_WhitelabelErrorViewConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.error.ContextBootstrapInitializer.registerWhitelabelErrorViewConfiguration_error(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.error.ContextBootstrapInitializer.registerWhitelabelErrorViewConfiguration_beanNameViewResolver(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.error.ContextBootstrapInitializer.registerErrorMvcAutoConfiguration_DefaultErrorViewResolverConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.error.ContextBootstrapInitializer.registerDefaultErrorViewResolverConfiguration_conventionErrorViewResolver(beanFactory);
    BeanDefinitionRegistrar.of("spring.web-org.springframework.boot.autoconfigure.web.WebProperties", WebProperties.class)
        .instanceSupplier(WebProperties::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration", ErrorMvcAutoConfiguration.class).withConstructor(ServerProperties.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> new ErrorMvcAutoConfiguration(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("errorAttributes", DefaultErrorAttributes.class).withFactoryMethod(ErrorMvcAutoConfiguration.class, "errorAttributes")
        .instanceSupplier(() -> beanFactory.getBean(ErrorMvcAutoConfiguration.class).errorAttributes()).register(beanFactory);
    BeanDefinitionRegistrar.of("basicErrorController", BasicErrorController.class).withFactoryMethod(ErrorMvcAutoConfiguration.class, "basicErrorController", ErrorAttributes.class, ObjectProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(ErrorMvcAutoConfiguration.class).basicErrorController(attributes.get(0), attributes.get(1)))).register(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.error.ContextBootstrapInitializer.registerErrorMvcAutoConfiguration_errorPageCustomizer(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.error.ContextBootstrapInitializer.registerErrorMvcAutoConfiguration_preserveErrorControllerTargetClassPostProcessor(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.ContextBootstrapInitializer.registerWebMvcAutoConfiguration_EnableWebMvcConfiguration(beanFactory);
    BeanDefinitionRegistrar.of("requestMappingHandlerAdapter", RequestMappingHandlerAdapter.class).withFactoryMethod(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class, "requestMappingHandlerAdapter", ContentNegotiationManager.class, FormattingConversionService.class, org.springframework.validation.Validator.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class).requestMappingHandlerAdapter(attributes.get(0), attributes.get(1), attributes.get(2)))).register(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.ContextBootstrapInitializer.registerEnableWebMvcConfiguration_welcomePageHandlerMapping(beanFactory);
    BeanDefinitionRegistrar.of("localeResolver", LocaleResolver.class).withFactoryMethod(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class, "localeResolver")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class).localeResolver()).register(beanFactory);
    BeanDefinitionRegistrar.of("themeResolver", ThemeResolver.class).withFactoryMethod(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class, "themeResolver")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class).themeResolver()).register(beanFactory);
    BeanDefinitionRegistrar.of("flashMapManager", FlashMapManager.class).withFactoryMethod(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class, "flashMapManager")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class).flashMapManager()).register(beanFactory);
    BeanDefinitionRegistrar.of("mvcConversionService", FormattingConversionService.class).withFactoryMethod(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class, "mvcConversionService")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class).mvcConversionService()).register(beanFactory);
    BeanDefinitionRegistrar.of("mvcValidator", org.springframework.validation.Validator.class).withFactoryMethod(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class, "mvcValidator")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class).mvcValidator()).register(beanFactory);
    BeanDefinitionRegistrar.of("mvcContentNegotiationManager", ContentNegotiationManager.class).withFactoryMethod(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class, "mvcContentNegotiationManager")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class).mvcContentNegotiationManager()).register(beanFactory);
    BeanDefinitionRegistrar.of("requestMappingHandlerMapping", RequestMappingHandlerMapping.class).withFactoryMethod(WebMvcConfigurationSupport.class, "requestMappingHandlerMapping", ContentNegotiationManager.class, FormattingConversionService.class, ResourceUrlProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(WebMvcConfigurationSupport.class).requestMappingHandlerMapping(attributes.get(0), attributes.get(1), attributes.get(2)))).register(beanFactory);
    BeanDefinitionRegistrar.of("mvcPatternParser", PathPatternParser.class).withFactoryMethod(WebMvcConfigurationSupport.class, "mvcPatternParser")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcConfigurationSupport.class).mvcPatternParser()).register(beanFactory);
    BeanDefinitionRegistrar.of("mvcUrlPathHelper", UrlPathHelper.class).withFactoryMethod(WebMvcConfigurationSupport.class, "mvcUrlPathHelper")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcConfigurationSupport.class).mvcUrlPathHelper()).register(beanFactory);
    BeanDefinitionRegistrar.of("mvcPathMatcher", PathMatcher.class).withFactoryMethod(WebMvcConfigurationSupport.class, "mvcPathMatcher")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcConfigurationSupport.class).mvcPathMatcher()).register(beanFactory);
    BeanDefinitionRegistrar.of("viewControllerHandlerMapping", HandlerMapping.class).withFactoryMethod(WebMvcConfigurationSupport.class, "viewControllerHandlerMapping", FormattingConversionService.class, ResourceUrlProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(WebMvcConfigurationSupport.class).viewControllerHandlerMapping(attributes.get(0), attributes.get(1)))).register(beanFactory);
    BeanDefinitionRegistrar.of("beanNameHandlerMapping", BeanNameUrlHandlerMapping.class).withFactoryMethod(WebMvcConfigurationSupport.class, "beanNameHandlerMapping", FormattingConversionService.class, ResourceUrlProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(WebMvcConfigurationSupport.class).beanNameHandlerMapping(attributes.get(0), attributes.get(1)))).register(beanFactory);
    BeanDefinitionRegistrar.of("routerFunctionMapping", RouterFunctionMapping.class).withFactoryMethod(WebMvcConfigurationSupport.class, "routerFunctionMapping", FormattingConversionService.class, ResourceUrlProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(WebMvcConfigurationSupport.class).routerFunctionMapping(attributes.get(0), attributes.get(1)))).register(beanFactory);
    BeanDefinitionRegistrar.of("resourceHandlerMapping", HandlerMapping.class).withFactoryMethod(WebMvcConfigurationSupport.class, "resourceHandlerMapping", ContentNegotiationManager.class, FormattingConversionService.class, ResourceUrlProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(WebMvcConfigurationSupport.class).resourceHandlerMapping(attributes.get(0), attributes.get(1), attributes.get(2)))).register(beanFactory);
    BeanDefinitionRegistrar.of("mvcResourceUrlProvider", ResourceUrlProvider.class).withFactoryMethod(WebMvcConfigurationSupport.class, "mvcResourceUrlProvider")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcConfigurationSupport.class).mvcResourceUrlProvider()).register(beanFactory);
    BeanDefinitionRegistrar.of("defaultServletHandlerMapping", HandlerMapping.class).withFactoryMethod(WebMvcConfigurationSupport.class, "defaultServletHandlerMapping")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcConfigurationSupport.class).defaultServletHandlerMapping()).register(beanFactory);
    BeanDefinitionRegistrar.of("handlerFunctionAdapter", HandlerFunctionAdapter.class).withFactoryMethod(WebMvcConfigurationSupport.class, "handlerFunctionAdapter")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcConfigurationSupport.class).handlerFunctionAdapter()).register(beanFactory);
    BeanDefinitionRegistrar.of("mvcUriComponentsContributor", CompositeUriComponentsContributor.class).withFactoryMethod(WebMvcConfigurationSupport.class, "mvcUriComponentsContributor", FormattingConversionService.class, RequestMappingHandlerAdapter.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(WebMvcConfigurationSupport.class).mvcUriComponentsContributor(attributes.get(0), attributes.get(1)))).register(beanFactory);
    BeanDefinitionRegistrar.of("httpRequestHandlerAdapter", HttpRequestHandlerAdapter.class).withFactoryMethod(WebMvcConfigurationSupport.class, "httpRequestHandlerAdapter")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcConfigurationSupport.class).httpRequestHandlerAdapter()).register(beanFactory);
    BeanDefinitionRegistrar.of("simpleControllerHandlerAdapter", SimpleControllerHandlerAdapter.class).withFactoryMethod(WebMvcConfigurationSupport.class, "simpleControllerHandlerAdapter")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcConfigurationSupport.class).simpleControllerHandlerAdapter()).register(beanFactory);
    BeanDefinitionRegistrar.of("handlerExceptionResolver", HandlerExceptionResolver.class).withFactoryMethod(WebMvcConfigurationSupport.class, "handlerExceptionResolver", ContentNegotiationManager.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(WebMvcConfigurationSupport.class).handlerExceptionResolver(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("mvcViewResolver", ViewResolver.class).withFactoryMethod(WebMvcConfigurationSupport.class, "mvcViewResolver", ContentNegotiationManager.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(WebMvcConfigurationSupport.class).mvcViewResolver(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("mvcHandlerMappingIntrospector", HandlerMappingIntrospector.class).withFactoryMethod(WebMvcConfigurationSupport.class, "mvcHandlerMappingIntrospector")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcConfigurationSupport.class).mvcHandlerMappingIntrospector()).customize((bd) -> bd.setLazyInit(true)).register(beanFactory);
    BeanDefinitionRegistrar.of("viewNameTranslator", RequestToViewNameTranslator.class).withFactoryMethod(WebMvcConfigurationSupport.class, "viewNameTranslator")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcConfigurationSupport.class).viewNameTranslator()).register(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.ContextBootstrapInitializer.registerWebMvcAutoConfiguration_WebMvcAutoConfigurationAdapter(beanFactory);
    BeanDefinitionRegistrar.of("defaultViewResolver", InternalResourceViewResolver.class).withFactoryMethod(WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter.class, "defaultViewResolver")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter.class).defaultViewResolver()).register(beanFactory);
    BeanDefinitionRegistrar.of("viewResolver", ContentNegotiatingViewResolver.class).withFactoryMethod(WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter.class, "viewResolver", BeanFactory.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter.class).viewResolver(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("requestContextFilter", RequestContextFilter.class).withFactoryMethod(WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter.class, "requestContextFilter")
        .instanceSupplier(() -> WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter.requestContextFilter()).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration", WebMvcAutoConfiguration.class)
        .instanceSupplier(WebMvcAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("formContentFilter", OrderedFormContentFilter.class).withFactoryMethod(WebMvcAutoConfiguration.class, "formContentFilter")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcAutoConfiguration.class).formContentFilter()).register(beanFactory);
    BeanDefinitionRegistrar.of("com.vaadin.flow.spring.VaadinScopesConfig", VaadinScopesConfig.class)
        .instanceSupplier(VaadinScopesConfig::new).register(beanFactory);
    BeanDefinitionRegistrar.of("vaadinSessionScope", BeanFactoryPostProcessor.class).withFactoryMethod(VaadinScopesConfig.class, "vaadinSessionScope")
        .instanceSupplier(() -> VaadinScopesConfig.vaadinSessionScope()).register(beanFactory);
    BeanDefinitionRegistrar.of("vaadinUIScope", BeanFactoryPostProcessor.class).withFactoryMethod(VaadinScopesConfig.class, "vaadinUIScope")
        .instanceSupplier(() -> VaadinScopesConfig.vaadinUIScope()).register(beanFactory);
    BeanDefinitionRegistrar.of("vaadinRouteScope", BeanFactoryPostProcessor.class).withFactoryMethod(VaadinScopesConfig.class, "vaadinRouteScope")
        .instanceSupplier(() -> VaadinScopesConfig.vaadinRouteScope()).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.aop.AopAutoConfiguration", AopAutoConfiguration.class)
        .instanceSupplier(AopAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.availability.ApplicationAvailabilityAutoConfiguration", ApplicationAvailabilityAutoConfiguration.class)
        .instanceSupplier(ApplicationAvailabilityAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("applicationAvailability", ApplicationAvailabilityBean.class).withFactoryMethod(ApplicationAvailabilityAutoConfiguration.class, "applicationAvailability")
        .instanceSupplier(() -> beanFactory.getBean(ApplicationAvailabilityAutoConfiguration.class).applicationAvailability()).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration", ConfigurationPropertiesAutoConfiguration.class)
        .instanceSupplier(ConfigurationPropertiesAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.context.LifecycleAutoConfiguration", LifecycleAutoConfiguration.class)
        .instanceSupplier(LifecycleAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("lifecycleProcessor", DefaultLifecycleProcessor.class).withFactoryMethod(LifecycleAutoConfiguration.class, "defaultLifecycleProcessor", LifecycleProperties.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(LifecycleAutoConfiguration.class).defaultLifecycleProcessor(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.lifecycle-org.springframework.boot.autoconfigure.context.LifecycleProperties", LifecycleProperties.class)
        .instanceSupplier(LifecycleProperties::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration", PersistenceExceptionTranslationAutoConfiguration.class)
        .instanceSupplier(PersistenceExceptionTranslationAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("persistenceExceptionTranslationPostProcessor", PersistenceExceptionTranslationPostProcessor.class).withFactoryMethod(PersistenceExceptionTranslationAutoConfiguration.class, "persistenceExceptionTranslationPostProcessor", Environment.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> PersistenceExceptionTranslationAutoConfiguration.persistenceExceptionTranslationPostProcessor(attributes.get(0)))).register(beanFactory);
    org.springframework.boot.autoconfigure.http.ContextBootstrapInitializer.registerHttpMessageConvertersAutoConfiguration_StringHttpMessageConverterConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.http.ContextBootstrapInitializer.registerStringHttpMessageConverterConfiguration_stringHttpMessageConverter(beanFactory);
    org.springframework.boot.autoconfigure.http.ContextBootstrapInitializer.registerJacksonHttpMessageConvertersConfiguration_MappingJackson2HttpMessageConverterConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.http.ContextBootstrapInitializer.registerMappingJackson2HttpMessageConverterConfiguration_mappingJackson2HttpMessageConverter(beanFactory);
    org.springframework.boot.autoconfigure.http.ContextBootstrapInitializer.registerJacksonHttpMessageConvertersConfiguration(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration", HttpMessageConvertersAutoConfiguration.class)
        .instanceSupplier(HttpMessageConvertersAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("messageConverters", HttpMessageConverters.class).withFactoryMethod(HttpMessageConvertersAutoConfiguration.class, "messageConverters", ObjectProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(HttpMessageConvertersAutoConfiguration.class).messageConverters(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.hateoas.mediatype.hal.HalMediaTypeConfiguration", HalMediaTypeConfiguration.class).withConstructor(LinkRelationProvider.class, ObjectProvider.class, ObjectProvider.class, MessageResolver.class, AutowireCapableBeanFactory.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> new HalMediaTypeConfiguration(attributes.get(0), attributes.get(1), attributes.get(2), attributes.get(3), attributes.get(4)))).register(beanFactory);
    org.springframework.hateoas.mediatype.hal.ContextBootstrapInitializer.registerHalMediaTypeConfiguration_halLinkDisocoverer(beanFactory);
    org.springframework.hateoas.mediatype.hal.forms.ContextBootstrapInitializer.registerHalFormsMediaTypeConfiguration(beanFactory);
    org.springframework.hateoas.mediatype.hal.forms.ContextBootstrapInitializer.registerHalFormsMediaTypeConfiguration_halFormsLinkDiscoverer(beanFactory);
    org.springframework.hateoas.mediatype.hal.forms.ContextBootstrapInitializer.registerHalFormsMediaTypeConfiguration_halFormsTemplatePropertyWriter(beanFactory);
    org.springframework.hateoas.config.ContextBootstrapInitializer.registerHateoasConfiguration(beanFactory);
    BeanDefinitionRegistrar.of("messageResolver", MessageResolver.class).withFactoryMethod(HateoasConfiguration.class, "messageResolver")
        .instanceSupplier(() -> beanFactory.getBean(HateoasConfiguration.class).messageResolver()).register(beanFactory);
    org.springframework.hateoas.config.ContextBootstrapInitializer.registerHateoasConfiguration_hypermediaWebMvcConverters(beanFactory);
    org.springframework.hateoas.config.ContextBootstrapInitializer.registerHateoasConfiguration_defaultRelProvider(beanFactory);
    org.springframework.hateoas.config.ContextBootstrapInitializer.registerHateoasConfiguration_annotationRelProvider(beanFactory);
    org.springframework.hateoas.config.ContextBootstrapInitializer.registerHateoasConfiguration__relProvider(beanFactory);
    org.springframework.hateoas.config.ContextBootstrapInitializer.registerHateoasConfiguration_relProviderPluginRegistry(beanFactory);
    org.springframework.hateoas.config.ContextBootstrapInitializer.registerHateoasConfiguration_linkDiscoverers(beanFactory);
    BeanDefinitionRegistrar.of("linkDiscovererRegistry", ResolvableType.forClassWithGenerics(OrderAwarePluginRegistry.class, LinkDiscoverer.class, MediaType.class))
        .instanceSupplier(PluginRegistryFactoryBean::new).customize((bd) -> bd.getPropertyValues().addPropertyValue("type", LinkDiscoverer.class)).register(beanFactory);
    org.springframework.hateoas.config.ContextBootstrapInitializer.registerWebMvcEntityLinksConfiguration(beanFactory);
    org.springframework.hateoas.config.ContextBootstrapInitializer.registerWebMvcEntityLinksConfiguration_webMvcEntityLinks(beanFactory);
    org.springframework.hateoas.config.ContextBootstrapInitializer.registerEntityLinksConfiguration_entityLinksPluginRegistry(beanFactory);
    org.springframework.hateoas.config.ContextBootstrapInitializer.registerEntityLinksConfiguration_delegatingEntityLinks(beanFactory);
    org.springframework.hateoas.config.ContextBootstrapInitializer.registerWebMvcHateoasConfiguration(beanFactory);
    org.springframework.hateoas.config.ContextBootstrapInitializer.registerWebMvcHateoasConfiguration_hypermediaWebMvcConfigurer(beanFactory);
    org.springframework.hateoas.config.ContextBootstrapInitializer.registerWebMvcHateoasConfiguration_representationModelProcessorInvoker(beanFactory);
    org.springframework.hateoas.config.ContextBootstrapInitializer.registerWebMvcHateoasConfiguration_hypermediaRepresentionModelProcessorConfigurator(beanFactory);
    org.springframework.hateoas.config.ContextBootstrapInitializer.registerWebMvcHateoasConfiguration_webMvcLinkBuilderFactory(beanFactory);
    org.springframework.hateoas.config.ContextBootstrapInitializer.registerRestTemplateHateoasConfiguration(beanFactory);
    org.springframework.hateoas.config.ContextBootstrapInitializer.registerRestTemplateHateoasConfiguration_hypermediaRestTemplateBeanPostProcessor(beanFactory);
    org.springframework.hateoas.config.ContextBootstrapInitializer.registerRestTemplateHateoasConfiguration_hypermediaRestTemplateConfigurer(beanFactory);
    org.springframework.hateoas.config.ContextBootstrapInitializer.registerWebFluxHateoasConfiguration(beanFactory);
    org.springframework.hateoas.config.ContextBootstrapInitializer.registerWebFluxHateoasConfiguration_hypermediaWebFluxConfigurer(beanFactory);
    org.springframework.hateoas.config.ContextBootstrapInitializer.registerWebFluxHateoasConfiguration_serverWebExchangeContextFilter(beanFactory);
    org.springframework.hateoas.config.ContextBootstrapInitializer.registerWebClientHateoasConfiguration(beanFactory);
    org.springframework.hateoas.config.ContextBootstrapInitializer.registerWebClientHateoasConfiguration_webClientConfigurer(beanFactory);
    org.springframework.hateoas.config.ContextBootstrapInitializer.registerWebClientHateoasConfiguration_webClientBeanPostProcessor(beanFactory);
    org.springframework.data.rest.webmvc.ContextBootstrapInitializer.registerRestControllerConfiguration(beanFactory);
    org.springframework.data.rest.webmvc.ContextBootstrapInitializer.registerRestControllerConfiguration_repositoryController(beanFactory);
    org.springframework.data.rest.webmvc.ContextBootstrapInitializer.registerRestControllerConfiguration_repositoryEntityController(beanFactory);
    org.springframework.data.rest.webmvc.ContextBootstrapInitializer.registerRestControllerConfiguration_repositoryPropertyReferenceController(beanFactory);
    org.springframework.data.rest.webmvc.ContextBootstrapInitializer.registerRestControllerConfiguration_repositorySearchController(beanFactory);
    org.springframework.data.rest.webmvc.ContextBootstrapInitializer.registerRestControllerConfiguration_repositorySchemaController(beanFactory);
    org.springframework.data.rest.webmvc.ContextBootstrapInitializer.registerRestControllerConfiguration_alpsController(beanFactory);
    org.springframework.data.rest.webmvc.ContextBootstrapInitializer.registerRestControllerConfiguration_profileController(beanFactory);
    org.springframework.data.rest.webmvc.halexplorer.ContextBootstrapInitializer.registerHalExplorerConfiguration(beanFactory);
    org.springframework.data.rest.webmvc.halexplorer.ContextBootstrapInitializer.registerHalExplorerConfiguration_halExplorer(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.data.web.config.SpringDataJacksonConfiguration", SpringDataJacksonConfiguration.class)
        .instanceSupplier(SpringDataJacksonConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("jacksonGeoModule", GeoModule.class).withFactoryMethod(SpringDataJacksonConfiguration.class, "jacksonGeoModule")
        .instanceSupplier(() -> beanFactory.getBean(SpringDataJacksonConfiguration.class).jacksonGeoModule()).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration", RepositoryRestMvcConfiguration.class).withConstructor(ApplicationContext.class, ObjectFactory.class, ObjectProvider.class, ObjectProvider.class, ObjectProvider.class, ObjectProvider.class, ObjectProvider.class, ObjectProvider.class, ObjectProvider.class, ObjectProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> new RepositoryRestMvcConfiguration(attributes.get(0), attributes.get(1), attributes.get(2), attributes.get(3), attributes.get(4), attributes.get(5), attributes.get(6), attributes.get(7), attributes.get(8), attributes.get(9)))).register(beanFactory);
    BeanDefinitionRegistrar.of("repositories", Repositories.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "repositories")
        .instanceSupplier(() -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).repositories()).register(beanFactory);
    BeanDefinitionRegistrar.of("repositoryRelProvider", RepositoryRelProvider.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "repositoryRelProvider", ObjectFactory.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).repositoryRelProvider(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("persistentEntities", PersistentEntities.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "persistentEntities")
        .instanceSupplier(() -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).persistentEntities()).register(beanFactory);
    BeanDefinitionRegistrar.of("defaultConversionService", DefaultFormattingConversionService.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "defaultConversionService", PersistentEntities.class, RepositoryInvokerFactory.class, Repositories.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).defaultConversionService(attributes.get(0), attributes.get(1), attributes.get(2)))).register(beanFactory);
    BeanDefinitionRegistrar.of("validatingRepositoryEventListener", ValidatingRepositoryEventListener.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "validatingRepositoryEventListener", ObjectFactory.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).validatingRepositoryEventListener(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("jpaHelper", JpaHelper.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "jpaHelper")
        .instanceSupplier(() -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).jpaHelper()).register(beanFactory);
    BeanDefinitionRegistrar.of("repositoryRestConfiguration", RepositoryRestConfiguration.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "repositoryRestConfiguration")
        .instanceSupplier(() -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).repositoryRestConfiguration()).register(beanFactory);
    BeanDefinitionRegistrar.of("projectionDefinitionRegistrar", ProjectionDefinitionRegistar.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "projectionDefinitionRegistrar", ObjectFactory.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> RepositoryRestMvcConfiguration.projectionDefinitionRegistrar(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("metadataConfiguration", MetadataConfiguration.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "metadataConfiguration")
        .instanceSupplier(() -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).metadataConfiguration()).register(beanFactory);
    BeanDefinitionRegistrar.of("baseUri", BaseUri.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "baseUri", RepositoryRestConfiguration.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).baseUri(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("annotatedEventHandlerInvoker", AnnotatedEventHandlerInvoker.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "annotatedEventHandlerInvoker")
        .instanceSupplier(() -> RepositoryRestMvcConfiguration.annotatedEventHandlerInvoker()).register(beanFactory);
    BeanDefinitionRegistrar.of("serverHttpRequestMethodArgumentResolver", ServerHttpRequestMethodArgumentResolver.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "serverHttpRequestMethodArgumentResolver")
        .instanceSupplier(() -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).serverHttpRequestMethodArgumentResolver()).register(beanFactory);
    BeanDefinitionRegistrar.of("repoRequestArgumentResolver", RootResourceInformationHandlerMethodArgumentResolver.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "repoRequestArgumentResolver", Repositories.class, ResourceMetadataHandlerMethodArgumentResolver.class, RepositoryInvokerFactory.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).repoRequestArgumentResolver(attributes.get(0), attributes.get(1), attributes.get(2)))).register(beanFactory);
    BeanDefinitionRegistrar.of("resourceMetadataHandlerMethodArgumentResolver", ResourceMetadataHandlerMethodArgumentResolver.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "resourceMetadataHandlerMethodArgumentResolver", Repositories.class, RepositoryResourceMappings.class, BaseUri.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).resourceMetadataHandlerMethodArgumentResolver(attributes.get(0), attributes.get(1), attributes.get(2)))).register(beanFactory);
    BeanDefinitionRegistrar.of("backendIdHandlerMethodArgumentResolver", BackendIdHandlerMethodArgumentResolver.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "backendIdHandlerMethodArgumentResolver", PluginRegistry.class, ResourceMetadataHandlerMethodArgumentResolver.class, BaseUri.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).backendIdHandlerMethodArgumentResolver(attributes.get(0), attributes.get(1), attributes.get(2)))).register(beanFactory);
    BeanDefinitionRegistrar.of("eTagArgumentResolver", ETagArgumentResolver.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "eTagArgumentResolver")
        .instanceSupplier(() -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).eTagArgumentResolver()).register(beanFactory);
    BeanDefinitionRegistrar.of("entityLinks", RepositoryEntityLinks.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "entityLinks", ObjectFactory.class, Repositories.class, RepositoryResourceMappings.class, PluginRegistry.class, RepositoryRestConfiguration.class, ObjectFactory.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).entityLinks(attributes.get(0), attributes.get(1), attributes.get(2), attributes.get(3), attributes.get(4), attributes.get(5)))).register(beanFactory);
    BeanDefinitionRegistrar.of("persistentEntityArgumentResolver", PersistentEntityResourceHandlerMethodArgumentResolver.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "persistentEntityArgumentResolver", List.class, RootResourceInformationHandlerMethodArgumentResolver.class, Associations.class, BackendIdHandlerMethodArgumentResolver.class, PersistentEntities.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).persistentEntityArgumentResolver(attributes.get(0), attributes.get(1), attributes.get(2), attributes.get(3), attributes.get(4)))).register(beanFactory);
    BeanDefinitionRegistrar.of("jsonSchemaConverter", PersistentEntityToJsonSchemaConverter.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "jsonSchemaConverter", PersistentEntities.class, Associations.class, RepositoryInvokerFactory.class, RepositoryRestConfiguration.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).jsonSchemaConverter(attributes.get(0), attributes.get(1), attributes.get(2), attributes.get(3)))).register(beanFactory);
    BeanDefinitionRegistrar.of("jacksonHttpMessageConverter", TypeConstrainedMappingJackson2HttpMessageConverter.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "jacksonHttpMessageConverter", RepositoryRestConfiguration.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).jacksonHttpMessageConverter(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("halJacksonHttpMessageConverter", TypeConstrainedMappingJackson2HttpMessageConverter.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "halJacksonHttpMessageConverter", LinkCollector.class, RepositoryRestConfiguration.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).halJacksonHttpMessageConverter(attributes.get(0), attributes.get(1)))).register(beanFactory);
    org.springframework.data.rest.webmvc.config.ContextBootstrapInitializer.registerRepositoryRestMvcConfiguration_halFormsJacksonHttpMessageConverter(beanFactory);
    BeanDefinitionRegistrar.of("uriListHttpMessageConverter", UriListHttpMessageConverter.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "uriListHttpMessageConverter")
        .instanceSupplier(() -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).uriListHttpMessageConverter()).register(beanFactory);
    BeanDefinitionRegistrar.of("repositoryExporterHandlerAdapter", RequestMappingHandlerAdapter.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "repositoryExporterHandlerAdapter", List.class, AlpsJsonHttpMessageConverter.class, SelfLinkProvider.class, PersistentEntityResourceHandlerMethodArgumentResolver.class, RootResourceInformationHandlerMethodArgumentResolver.class, RepositoryRestConfiguration.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).repositoryExporterHandlerAdapter(attributes.get(0), attributes.get(1), attributes.get(2), attributes.get(3), attributes.get(4), attributes.get(5)))).register(beanFactory);
    org.springframework.data.rest.webmvc.config.ContextBootstrapInitializer.registerRepositoryRestMvcConfiguration_restHandlerMapping(beanFactory);
    BeanDefinitionRegistrar.of("resourceMappings", RepositoryResourceMappings.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "resourceMappings", Repositories.class, PersistentEntities.class, RepositoryRestConfiguration.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).resourceMappings(attributes.get(0), attributes.get(1), attributes.get(2)))).register(beanFactory);
    org.springframework.data.rest.webmvc.config.ContextBootstrapInitializer.registerRepositoryRestMvcConfiguration_linkCollector(beanFactory);
    BeanDefinitionRegistrar.of("excerptProjector", ExcerptProjector.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "excerptProjector", RepositoryResourceMappings.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).excerptProjector(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("repositoryRestExceptionHandler", RepositoryRestExceptionHandler.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "repositoryRestExceptionHandler")
        .instanceSupplier(() -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).repositoryRestExceptionHandler()).register(beanFactory);
    BeanDefinitionRegistrar.of("repositoryInvokerFactory", RepositoryInvokerFactory.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "repositoryInvokerFactory")
        .instanceSupplier(() -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).repositoryInvokerFactory()).register(beanFactory);
    BeanDefinitionRegistrar.of("defaultMessageConverters", List.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "defaultMessageConverters", TypeConstrainedMappingJackson2HttpMessageConverter.class, TypeConstrainedMappingJackson2HttpMessageConverter.class, TypeConstrainedMappingJackson2HttpMessageConverter.class, AlpsJsonHttpMessageConverter.class, UriListHttpMessageConverter.class, RepositoryRestConfiguration.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).defaultMessageConverters(attributes.get(0), attributes.get(1), attributes.get(2), attributes.get(3), attributes.get(4), attributes.get(5)))).register(beanFactory);
    BeanDefinitionRegistrar.of("alpsJsonHttpMessageConverter", AlpsJsonHttpMessageConverter.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "alpsJsonHttpMessageConverter", RootResourceInformationToAlpsDescriptorConverter.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).alpsJsonHttpMessageConverter(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("pageableResolver", HateoasPageableHandlerMethodArgumentResolver.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "pageableResolver")
        .instanceSupplier(() -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).pageableResolver()).register(beanFactory);
    BeanDefinitionRegistrar.of("sortResolver", HateoasSortHandlerMethodArgumentResolver.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "sortResolver")
        .instanceSupplier(() -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).sortResolver()).register(beanFactory);
    BeanDefinitionRegistrar.of("backendIdConverterRegistry", PluginRegistry.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "backendIdConverterRegistry", List.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).backendIdConverterRegistry(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("auditableBeanWrapperFactory", AuditableBeanWrapperFactory.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "auditableBeanWrapperFactory", PersistentEntities.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).auditableBeanWrapperFactory(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("httpHeadersPreparer", HttpHeadersPreparer.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "httpHeadersPreparer", AuditableBeanWrapperFactory.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).httpHeadersPreparer(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("selfLinkProvider", SelfLinkProvider.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "selfLinkProvider", PersistentEntities.class, RepositoryEntityLinks.class, ObjectProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).selfLinkProvider(attributes.get(0), attributes.get(1), attributes.get(2)))).register(beanFactory);
    BeanDefinitionRegistrar.of("associationLinks", Associations.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "associationLinks", RepositoryResourceMappings.class, RepositoryRestConfiguration.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).associationLinks(attributes.get(0), attributes.get(1)))).register(beanFactory);
    BeanDefinitionRegistrar.of("enumTranslator", EnumTranslator.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "enumTranslator", MessageResolver.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).enumTranslator(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("alpsConverter", RootResourceInformationToAlpsDescriptorConverter.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "alpsConverter", Repositories.class, PersistentEntities.class, RepositoryEntityLinks.class, EnumTranslator.class, Associations.class, RepositoryRestConfiguration.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).alpsConverter(attributes.get(0), attributes.get(1), attributes.get(2), attributes.get(3), attributes.get(4), attributes.get(5)))).register(beanFactory);
    BeanDefinitionRegistrar.of("profileResourceProcessor", ProfileResourceProcessor.class).withFactoryMethod(RepositoryRestMvcConfiguration.class, "profileResourceProcessor", RepositoryRestConfiguration.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RepositoryRestMvcConfiguration.class).profileResourceProcessor(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("pagedResourcesAssembler", PagedResourcesAssembler.class).withFactoryMethod(HateoasAwareSpringDataWebConfiguration.class, "pagedResourcesAssembler")
        .instanceSupplier(() -> beanFactory.getBean(HateoasAwareSpringDataWebConfiguration.class).pagedResourcesAssembler()).register(beanFactory);
    BeanDefinitionRegistrar.of("pagedResourcesAssemblerArgumentResolver", PagedResourcesAssemblerArgumentResolver.class).withFactoryMethod(HateoasAwareSpringDataWebConfiguration.class, "pagedResourcesAssemblerArgumentResolver")
        .instanceSupplier(() -> beanFactory.getBean(HateoasAwareSpringDataWebConfiguration.class).pagedResourcesAssemblerArgumentResolver()).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration", RepositoryRestMvcAutoConfiguration.class)
        .instanceSupplier(RepositoryRestMvcAutoConfiguration::new).register(beanFactory);
    org.springframework.boot.autoconfigure.data.rest.ContextBootstrapInitializer.registerRepositoryRestMvcAutoConfiguration_springBootRepositoryRestConfigurer(beanFactory);
    BeanDefinitionRegistrar.of("spring.data.rest-org.springframework.boot.autoconfigure.data.rest.RepositoryRestProperties", RepositoryRestProperties.class)
        .instanceSupplier(RepositoryRestProperties::new).register(beanFactory);
    org.springframework.boot.autoconfigure.freemarker.ContextBootstrapInitializer.registerFreeMarkerServletWebConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.freemarker.ContextBootstrapInitializer.registerFreeMarkerServletWebConfiguration_freeMarkerConfigurer(beanFactory);
    org.springframework.boot.autoconfigure.freemarker.ContextBootstrapInitializer.registerFreeMarkerServletWebConfiguration_freeMarkerConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.freemarker.ContextBootstrapInitializer.registerFreeMarkerServletWebConfiguration_freeMarkerViewResolver(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration", FreeMarkerAutoConfiguration.class).withConstructor(ApplicationContext.class, FreeMarkerProperties.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> new FreeMarkerAutoConfiguration(attributes.get(0), attributes.get(1)))).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.freemarker-org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties", FreeMarkerProperties.class)
        .instanceSupplier(FreeMarkerProperties::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration", HypermediaAutoConfiguration.class)
        .instanceSupplier(HypermediaAutoConfiguration::new).register(beanFactory);
    org.springframework.boot.autoconfigure.hateoas.ContextBootstrapInitializer.registerHypermediaAutoConfiguration_applicationJsonHalConfiguration(beanFactory);
    BeanDefinitionRegistrar.of("spring.hateoas-org.springframework.boot.autoconfigure.hateoas.HateoasProperties", HateoasProperties.class)
        .instanceSupplier(HateoasProperties::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration", ProjectInfoAutoConfiguration.class).withConstructor(ProjectInfoProperties.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> new ProjectInfoAutoConfiguration(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.info-org.springframework.boot.autoconfigure.info.ProjectInfoProperties", ProjectInfoProperties.class)
        .instanceSupplier(ProjectInfoProperties::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.netty.NettyAutoConfiguration", NettyAutoConfiguration.class).withConstructor(NettyProperties.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> new NettyAutoConfiguration(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.netty-org.springframework.boot.autoconfigure.netty.NettyProperties", NettyProperties.class)
        .instanceSupplier(NettyProperties::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration", SqlInitializationAutoConfiguration.class)
        .instanceSupplier(SqlInitializationAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.sql.init-org.springframework.boot.autoconfigure.sql.init.SqlInitializationProperties", SqlInitializationProperties.class)
        .instanceSupplier(SqlInitializationProperties::new).register(beanFactory);
    org.springframework.boot.sql.init.dependency.ContextBootstrapInitializer.registerDatabaseInitializationDependencyConfigurer_DependsOnDatabaseInitializationPostProcessor(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration", TaskSchedulingAutoConfiguration.class)
        .instanceSupplier(TaskSchedulingAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("scheduledBeanLazyInitializationExcludeFilter", LazyInitializationExcludeFilter.class).withFactoryMethod(TaskSchedulingAutoConfiguration.class, "scheduledBeanLazyInitializationExcludeFilter")
        .instanceSupplier(() -> TaskSchedulingAutoConfiguration.scheduledBeanLazyInitializationExcludeFilter()).register(beanFactory);
    BeanDefinitionRegistrar.of("taskSchedulerBuilder", TaskSchedulerBuilder.class).withFactoryMethod(TaskSchedulingAutoConfiguration.class, "taskSchedulerBuilder", TaskSchedulingProperties.class, ObjectProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(TaskSchedulingAutoConfiguration.class).taskSchedulerBuilder(attributes.get(0), attributes.get(1)))).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.task.scheduling-org.springframework.boot.autoconfigure.task.TaskSchedulingProperties", TaskSchedulingProperties.class)
        .instanceSupplier(TaskSchedulingProperties::new).register(beanFactory);
    org.springframework.boot.autoconfigure.thymeleaf.ContextBootstrapInitializer.registerThymeleafAutoConfiguration_ThymeleafJava8TimeDialect(beanFactory);
    org.springframework.boot.autoconfigure.thymeleaf.ContextBootstrapInitializer.registerThymeleafJava8TimeDialect_java8TimeDialect(beanFactory);
    org.springframework.boot.autoconfigure.thymeleaf.ContextBootstrapInitializer.registerThymeleafWebMvcConfiguration_ThymeleafViewResolverConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.thymeleaf.ContextBootstrapInitializer.registerThymeleafViewResolverConfiguration_thymeleafViewResolver(beanFactory);
    org.springframework.boot.autoconfigure.thymeleaf.ContextBootstrapInitializer.registerThymeleafAutoConfiguration_ThymeleafWebMvcConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.thymeleaf.ContextBootstrapInitializer.registerThymeleafAutoConfiguration_DefaultTemplateResolverConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.thymeleaf.ContextBootstrapInitializer.registerDefaultTemplateResolverConfiguration_defaultTemplateResolver(beanFactory);
    org.springframework.boot.autoconfigure.thymeleaf.ContextBootstrapInitializer.registerTemplateEngineConfigurations_DefaultTemplateEngineConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.thymeleaf.ContextBootstrapInitializer.registerDefaultTemplateEngineConfiguration_templateEngine(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration", ThymeleafAutoConfiguration.class)
        .instanceSupplier(ThymeleafAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.thymeleaf-org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties", ThymeleafProperties.class)
        .instanceSupplier(ThymeleafProperties::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration", TransactionAutoConfiguration.class)
        .instanceSupplier(TransactionAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("platformTransactionManagerCustomizers", TransactionManagerCustomizers.class).withFactoryMethod(TransactionAutoConfiguration.class, "platformTransactionManagerCustomizers", ObjectProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(TransactionAutoConfiguration.class).platformTransactionManagerCustomizers(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.transaction-org.springframework.boot.autoconfigure.transaction.TransactionProperties", TransactionProperties.class)
        .instanceSupplier(TransactionProperties::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration", RestTemplateAutoConfiguration.class)
        .instanceSupplier(RestTemplateAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("restTemplateBuilderConfigurer", RestTemplateBuilderConfigurer.class).withFactoryMethod(RestTemplateAutoConfiguration.class, "restTemplateBuilderConfigurer", ObjectProvider.class, ObjectProvider.class, ObjectProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RestTemplateAutoConfiguration.class).restTemplateBuilderConfigurer(attributes.get(0), attributes.get(1), attributes.get(2)))).customize((bd) -> bd.setLazyInit(true)).register(beanFactory);
    BeanDefinitionRegistrar.of("restTemplateBuilder", RestTemplateBuilder.class).withFactoryMethod(RestTemplateAutoConfiguration.class, "restTemplateBuilder", RestTemplateBuilderConfigurer.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RestTemplateAutoConfiguration.class).restTemplateBuilder(attributes.get(0)))).customize((bd) -> bd.setLazyInit(true)).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration$NettyWebServerFactoryCustomizerConfiguration", EmbeddedWebServerFactoryCustomizerAutoConfiguration.NettyWebServerFactoryCustomizerConfiguration.class)
        .instanceSupplier(EmbeddedWebServerFactoryCustomizerAutoConfiguration.NettyWebServerFactoryCustomizerConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("nettyWebServerFactoryCustomizer", NettyWebServerFactoryCustomizer.class).withFactoryMethod(EmbeddedWebServerFactoryCustomizerAutoConfiguration.NettyWebServerFactoryCustomizerConfiguration.class, "nettyWebServerFactoryCustomizer", Environment.class, ServerProperties.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(EmbeddedWebServerFactoryCustomizerAutoConfiguration.NettyWebServerFactoryCustomizerConfiguration.class).nettyWebServerFactoryCustomizer(attributes.get(0), attributes.get(1)))).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration$TomcatWebServerFactoryCustomizerConfiguration", EmbeddedWebServerFactoryCustomizerAutoConfiguration.TomcatWebServerFactoryCustomizerConfiguration.class)
        .instanceSupplier(EmbeddedWebServerFactoryCustomizerAutoConfiguration.TomcatWebServerFactoryCustomizerConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("tomcatWebServerFactoryCustomizer", TomcatWebServerFactoryCustomizer.class).withFactoryMethod(EmbeddedWebServerFactoryCustomizerAutoConfiguration.TomcatWebServerFactoryCustomizerConfiguration.class, "tomcatWebServerFactoryCustomizer", Environment.class, ServerProperties.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(EmbeddedWebServerFactoryCustomizerAutoConfiguration.TomcatWebServerFactoryCustomizerConfiguration.class).tomcatWebServerFactoryCustomizer(attributes.get(0), attributes.get(1)))).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration", EmbeddedWebServerFactoryCustomizerAutoConfiguration.class)
        .instanceSupplier(EmbeddedWebServerFactoryCustomizerAutoConfiguration::new).register(beanFactory);
    org.springframework.boot.autoconfigure.web.reactive.function.client.ContextBootstrapInitializer.registerClientHttpConnectorConfiguration_ReactorNetty(beanFactory);
    org.springframework.boot.autoconfigure.web.reactive.function.client.ContextBootstrapInitializer.registerReactorNetty_reactorClientResourceFactory(beanFactory);
    org.springframework.boot.autoconfigure.web.reactive.function.client.ContextBootstrapInitializer.registerReactorNetty_reactorClientHttpConnector(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.web.reactive.function.client.ClientHttpConnectorAutoConfiguration", ClientHttpConnectorAutoConfiguration.class)
        .instanceSupplier(ClientHttpConnectorAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("clientConnectorCustomizer", WebClientCustomizer.class).withFactoryMethod(ClientHttpConnectorAutoConfiguration.class, "clientConnectorCustomizer", ClientHttpConnector.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(ClientHttpConnectorAutoConfiguration.class).clientConnectorCustomizer(attributes.get(0)))).customize((bd) -> bd.setLazyInit(true)).register(beanFactory);
    org.springframework.boot.autoconfigure.web.reactive.function.client.ContextBootstrapInitializer.registerWebClientAutoConfiguration_WebClientCodecsConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.web.reactive.function.client.ContextBootstrapInitializer.registerWebClientCodecsConfiguration_exchangeStrategiesCustomizer(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration", WebClientAutoConfiguration.class)
        .instanceSupplier(WebClientAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("webClientBuilder", WebClient.Builder.class).withFactoryMethod(WebClientAutoConfiguration.class, "webClientBuilder", ObjectProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(WebClientAutoConfiguration.class).webClientBuilder(attributes.get(0)))).customize((bd) -> bd.setScope("prototype")).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration", HttpEncodingAutoConfiguration.class).withConstructor(ServerProperties.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> new HttpEncodingAutoConfiguration(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("characterEncodingFilter", CharacterEncodingFilter.class).withFactoryMethod(HttpEncodingAutoConfiguration.class, "characterEncodingFilter")
        .instanceSupplier(() -> beanFactory.getBean(HttpEncodingAutoConfiguration.class).characterEncodingFilter()).register(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.ContextBootstrapInitializer.registerHttpEncodingAutoConfiguration_localeCharsetMappingsCustomizer(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration", MultipartAutoConfiguration.class).withConstructor(MultipartProperties.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> new MultipartAutoConfiguration(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("multipartConfigElement", MultipartConfigElement.class).withFactoryMethod(MultipartAutoConfiguration.class, "multipartConfigElement")
        .instanceSupplier(() -> beanFactory.getBean(MultipartAutoConfiguration.class).multipartConfigElement()).register(beanFactory);
    BeanDefinitionRegistrar.of("multipartResolver", StandardServletMultipartResolver.class).withFactoryMethod(MultipartAutoConfiguration.class, "multipartResolver")
        .instanceSupplier(() -> beanFactory.getBean(MultipartAutoConfiguration.class).multipartResolver()).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.servlet.multipart-org.springframework.boot.autoconfigure.web.servlet.MultipartProperties", MultipartProperties.class)
        .instanceSupplier(MultipartProperties::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.ws.config.annotation.DelegatingWsConfiguration", DelegatingWsConfiguration.class)
        .instanceSupplier((instanceContext) -> {
          DelegatingWsConfiguration bean = new DelegatingWsConfiguration();
          instanceContext.method("setConfigurers", List.class)
              .resolve(beanFactory, false).ifResolved((attributes) -> bean.setConfigurers(attributes.get(0)));
          return bean;
        }).register(beanFactory);
    BeanDefinitionRegistrar.of("payloadRootAnnotationMethodEndpointMapping", PayloadRootAnnotationMethodEndpointMapping.class).withFactoryMethod(WsConfigurationSupport.class, "payloadRootAnnotationMethodEndpointMapping")
        .instanceSupplier(() -> beanFactory.getBean(WsConfigurationSupport.class).payloadRootAnnotationMethodEndpointMapping()).register(beanFactory);
    BeanDefinitionRegistrar.of("soapActionAnnotationMethodEndpointMapping", SoapActionAnnotationMethodEndpointMapping.class).withFactoryMethod(WsConfigurationSupport.class, "soapActionAnnotationMethodEndpointMapping")
        .instanceSupplier(() -> beanFactory.getBean(WsConfigurationSupport.class).soapActionAnnotationMethodEndpointMapping()).register(beanFactory);
    BeanDefinitionRegistrar.of("annotationActionEndpointMapping", AnnotationActionEndpointMapping.class).withFactoryMethod(WsConfigurationSupport.class, "annotationActionEndpointMapping")
        .instanceSupplier(() -> beanFactory.getBean(WsConfigurationSupport.class).annotationActionEndpointMapping()).register(beanFactory);
    BeanDefinitionRegistrar.of("defaultMethodEndpointAdapter", DefaultMethodEndpointAdapter.class).withFactoryMethod(WsConfigurationSupport.class, "defaultMethodEndpointAdapter")
        .instanceSupplier(() -> beanFactory.getBean(WsConfigurationSupport.class).defaultMethodEndpointAdapter()).register(beanFactory);
    BeanDefinitionRegistrar.of("soapFaultAnnotationExceptionResolver", SoapFaultAnnotationExceptionResolver.class).withFactoryMethod(WsConfigurationSupport.class, "soapFaultAnnotationExceptionResolver")
        .instanceSupplier(() -> beanFactory.getBean(WsConfigurationSupport.class).soapFaultAnnotationExceptionResolver()).register(beanFactory);
    BeanDefinitionRegistrar.of("simpleSoapExceptionResolver", SimpleSoapExceptionResolver.class).withFactoryMethod(WsConfigurationSupport.class, "simpleSoapExceptionResolver")
        .instanceSupplier(() -> beanFactory.getBean(WsConfigurationSupport.class).simpleSoapExceptionResolver()).register(beanFactory);
    org.springframework.boot.autoconfigure.webservices.ContextBootstrapInitializer.registerWebServicesAutoConfiguration_WsConfiguration(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration", WebServicesAutoConfiguration.class)
        .instanceSupplier(WebServicesAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("messageDispatcherServlet", ResolvableType.forClassWithGenerics(ServletRegistrationBean.class, MessageDispatcherServlet.class)).withFactoryMethod(WebServicesAutoConfiguration.class, "messageDispatcherServlet", ApplicationContext.class, WebServicesProperties.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(WebServicesAutoConfiguration.class).messageDispatcherServlet(attributes.get(0), attributes.get(1)))).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.webservices-org.springframework.boot.autoconfigure.webservices.WebServicesProperties", WebServicesProperties.class)
        .instanceSupplier(WebServicesProperties::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.webservices.client.WebServiceTemplateAutoConfiguration", WebServiceTemplateAutoConfiguration.class)
        .instanceSupplier(WebServiceTemplateAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("webServiceTemplateBuilder", WebServiceTemplateBuilder.class).withFactoryMethod(WebServiceTemplateAutoConfiguration.class, "webServiceTemplateBuilder", ObjectProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(WebServiceTemplateAutoConfiguration.class).webServiceTemplateBuilder(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.websocket.servlet.WebSocketMessagingAutoConfiguration", WebSocketMessagingAutoConfiguration.class)
        .instanceSupplier(WebSocketMessagingAutoConfiguration::new).register(beanFactory);
  }
}
