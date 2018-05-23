package com.bert.cloud;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**
 * 自定义ribbon负载算法策略(默认轮循)
 */
@Configuration
@ExcludeFromComponentScan
public class TestConfiguration {

  @Bean
  public IRule ribbonRule() {
	 //随机策略
    return new RandomRule();
  }
}
