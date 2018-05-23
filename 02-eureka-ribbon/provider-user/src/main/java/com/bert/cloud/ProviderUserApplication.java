package com.bert.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * 可以启动多个provider-user节点，只需要修改application.yml中server:port即可
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class ProviderUserApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProviderUserApplication.class, args);
  }
}
