package com.springboot.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/rest/user")
@Api(value = "User Resource REST Endpoint", description = "Shows the user info")
public class UserController {

  @GetMapping
  public List<User> getUsers() {

    return Arrays.asList(new User("Sam", 2000L), new User("Peter", 1000L));
  }

  @GetMapping("/{userName}")
  public User getUser(@PathVariable("userName") final String userName) {
    return new User(userName, 1000L);
  }

  @Setter
  @Getter
  @AllArgsConstructor
  private class User {

    @ApiModelProperty(notes = "name of the User")
    private String userName;

    @ApiModelProperty(notes = "salary of the user")
    private Long salary;
  }
}
