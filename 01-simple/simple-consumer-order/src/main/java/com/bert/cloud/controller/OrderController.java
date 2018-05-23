package com.bert.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bert.cloud.entity.User;

@RestController
public class OrderController {
  @Autowired
  private RestTemplate restTemplate;

  @Value("${user.userServicePath}")
  private String userServicePath;

  @GetMapping("/order/{id}")
  public User findById(@PathVariable Long id) {
    return this.restTemplate.getForObject(this.userServicePath + id, User.class);
  }
}
