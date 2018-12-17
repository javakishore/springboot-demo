package com.springboot.demo.config;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.properties.PropertyValueEncryptionUtils;
import org.jasypt.util.password.BasicPasswordEncryptor;

public class Test {
  public static void main(String... args) {
    EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
    config.setPassword(AppConstants.PBE_KEY);
    config.setAlgorithm("PBEWithMD5AndDES");
    config.setKeyObtentionIterations("1000");
    config.setPoolSize("1");
    config.setProviderName("SunJCE");
    config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
    config.setStringOutputType("base64");

    PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
    encryptor.setConfig(config);

    BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
    String encryptedPassword = passwordEncryptor.encryptPassword("dbuser");

    System.out.println(PropertyValueEncryptionUtils.encrypt("dbuser", encryptor));
  }
}
