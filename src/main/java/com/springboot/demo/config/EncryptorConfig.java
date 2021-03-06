package com.springboot.demo.config;

import com.springboot.demo.constants.AppConstants;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncryptorConfig {

  @Bean
  public static EnvironmentStringPBEConfig environmentVariablesConfiguration() {
    EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
    //config.setPasswordEnvName("jasypt.encryptor.password");
    config.setPassword(AppConstants.PBE_KEY);
    config.setAlgorithm("PBEWithMD5AndDES");
    config.setKeyObtentionIterations("1000");
    config.setPoolSize("1");
    config.setProviderName("SunJCE");
    config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
    config.setStringOutputType("base64");
    return config;
  }

  @Bean(name = "jasyptStringEncryptor")
  public static PooledPBEStringEncryptor stringEncryptor() {
    PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
    encryptor.setConfig(environmentVariablesConfiguration());
    return encryptor;
  }
 /* public static void main(String...args){
    PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
    encryptor.setConfig(environmentVariablesConfiguration());
    System.out.println(encryptor.encrypt("dbuser"));
    System.out.println(encryptor.decrypt("3Z3J3SJAhqMlCkRZHqA1tQ=="));
  }*/
}
