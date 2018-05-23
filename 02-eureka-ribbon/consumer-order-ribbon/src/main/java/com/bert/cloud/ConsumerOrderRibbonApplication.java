package com.bert.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
//provider-user自定义ribbon负载策略(随机)
//其他provider-user服务，还是默认轮询策略
@RibbonClient(name = "provider-user", configuration = TestConfiguration.class)
@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeFromComponentScan.class) })
public class ConsumerOrderRibbonApplication {

  /**
   * @LoadBalanced  负载均衡支持
   * 测试时，开始基本所有请求都会到第一个节点服务，后来才会有请求负载到其他节点，这是为什么呢？
   * 这是因为服务注册到ribbon需要一个过程
   * 默认策略：轮训，请求分配的比较均衡
   */
  @Bean
  @LoadBalanced
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  public static void main(String[] args) {
    SpringApplication.run(ConsumerOrderRibbonApplication.class, args);
  }
}
