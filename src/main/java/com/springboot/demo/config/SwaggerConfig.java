package com.springboot.demo.config;

import com.fasterxml.jackson.databind.JsonSerializer;
import org.omg.CORBA.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerMapping;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiResourceController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2Mapper;
import springfox.documentation.swagger2.web.Swagger2Controller;

import javax.servlet.ServletContext;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

  @Bean
  public Docket productApi(ServletContext servletContext) {
    return new Docket(DocumentationType.SWAGGER_2)
        .host("localhost:8088")
        .pathProvider(
            new RelativePathProvider(servletContext) {

              @Override
              public String getApplicationBasePath() {
                return "/spring-demo";
              }

              @Override
              public String applicationPath() {
                return "/myapp";
              }

              @Override
              public String getDocumentationPath() {
                return "/myapp";
              }
            })
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.springboot.demo"))
        .paths(regex("/rest.*"))
        .build()
        .apiInfo(metaInfo());
  }

  private ApiInfo metaInfo() {

    ApiInfo apiInfo =
        new ApiInfo(
            "Spring Boot Swagger Example API",
            "Spring Boot Swagger Example API for UI",
            "1.0",
            "Terms of Service",
            new Contact("Developers", "http://developer.com", "developers@gmail.com"),
            "Apache License Version 2.0",
            "https://www.apache.org/licesen.html");

    return apiInfo;
  }
}
