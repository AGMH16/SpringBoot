package org.springframework.hateoas.mediatype.uber;

public abstract class _FactoryProvider {
  public static UberMediaTypeConfigurationProvider uberMediaTypeConfigurationProvider() {
    return new UberMediaTypeConfigurationProvider();
  }

  public static UberAffordanceModelFactory uberAffordanceModelFactory() {
    return new UberAffordanceModelFactory();
  }
}
