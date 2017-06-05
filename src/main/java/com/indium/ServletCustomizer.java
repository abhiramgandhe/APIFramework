package com.indium;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Configuration
public class ServletCustomizer {
  @Bean
  public EmbeddedServletContainerCustomizer customizer() {
    return container -> {
      container.addErrorPages(
          new ErrorPage(HttpStatus.UNAUTHORIZED, "/unauthenticated"));
    };
  }
  
}
