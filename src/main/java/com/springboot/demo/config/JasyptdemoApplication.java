package com.springboot.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class JasyptdemoApplication {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Value("${db.driverclassname}")
  private String dbDriverClassName;

  @Value("${db.url}")
  private String dbUrl;

  @Value("${db.username}")
  private String dbUsername;

  @Value("${db.password}")
  private String dbPassword;

  public static void main(String[] args) {
    System.setProperty("jasypt.encryptor.password", "somepassword");
    SpringApplication.run(JasyptdemoApplication.class, args);
  }

  @Bean
  public DataSource dataSource() {
    DataSource ds =
        DataSourceBuilder.create()
            .username(dbUsername)
            .password(dbPassword)
            .url(dbUrl)
            .driverClassName(dbDriverClassName)
            .build();
    System.out.println("ds = " + ds);
    return ds;
  }

  @Bean
  public EmbeddedServletContainerFactory servletContainer() {
    TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
    return factory;
  }

  @RestController
  class Hello {
    @GetMapping("/hello")
    public String hello() {
      return "Hello World";
    }
  }
}
