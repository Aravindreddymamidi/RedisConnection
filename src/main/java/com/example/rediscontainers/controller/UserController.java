package com.example.rediscontainers.controller;

import com.example.rediscontainers.model.User;
import com.example.rediscontainers.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/users")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    User user1 = userService.saveUser(user);
    return ResponseEntity.ok(user1);

  }

  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUser(@PathVariable String id) {
    User user = userService.getUser(id);
    return ResponseEntity.ok(user);
  }

  // other methods
}
