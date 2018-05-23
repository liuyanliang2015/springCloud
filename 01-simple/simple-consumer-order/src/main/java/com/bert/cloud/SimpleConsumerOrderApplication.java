package com.bert.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SimpleConsumerOrderApplication {

	
/**
 * Consider defining a bean of type 'org.springframework.web.client.RestTemplate' 
 * in your configuration.
 * 启动类上，创建 RestTemplate实例
 */
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  public static void main(String[] args) {
    SpringApplication.run(SimpleConsumerOrderApplication.class, args);
  }
}
