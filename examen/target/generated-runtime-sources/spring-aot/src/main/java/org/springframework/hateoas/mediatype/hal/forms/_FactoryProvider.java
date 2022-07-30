package org.springframework.hateoas.mediatype.hal.forms;

public abstract class _FactoryProvider {
  public static HalFormsMediaTypeConfigurationProvider halFormsMediaTypeConfigurationProvider() {
    return new HalFormsMediaTypeConfigurationProvider();
  }

  public static HalFormsAffordanceModelFactory halFormsAffordanceModelFactory() {
    return new HalFormsAffordanceModelFactory();
  }
}
