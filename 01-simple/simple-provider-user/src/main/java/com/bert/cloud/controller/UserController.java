package com.bert.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bert.cloud.entity.User;
import com.bert.cloud.service.UserService;

/**
 * @RestController也是组合注解
 * @Controller + @ResponseBody
 */
@RestController
public class UserController {

  @Autowired
  private UserService userService;

  /**
   * @GetMapping spring4.3之后开始支持
   * 等价于RequestMapping(value="/simple/{id}",method = RequestMethod.GET)
   */
  @GetMapping("/simple/{id}")
  public User findById(@PathVariable Long id) {
    return this.userService.findOne(id);
  }
}
