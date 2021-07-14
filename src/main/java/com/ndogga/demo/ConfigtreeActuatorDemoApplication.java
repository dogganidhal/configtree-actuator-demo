package com.ndogga.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ConstructorBinding
record NestedDemoConfigurationProperties(
        String nestedProp1
) { }


@ConstructorBinding
@ConfigurationProperties(prefix = "com.ndogga")
record DemoConfigurationProperties(
        String prop1,
        String prop2,
        @NestedConfigurationProperty
        NestedDemoConfigurationProperties nested
) { }


@RestController
class DemoController {

  private final DemoConfigurationProperties configuration;

  @Autowired
  DemoController(DemoConfigurationProperties configuration) {
    this.configuration = configuration;
  }

  @GetMapping("/configuration")
  public DemoConfigurationProperties greet() {
    return configuration;
  }

}


@SpringBootApplication
@EnableConfigurationProperties(DemoConfigurationProperties.class)
public class ConfigtreeActuatorDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(ConfigtreeActuatorDemoApplication.class, args);
  }

}
