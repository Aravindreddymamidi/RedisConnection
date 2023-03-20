package com.example.rediscontainers.controller;

import com.example.rediscontainers.model.User;
import com.example.rediscontainers.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserControllerTest {
  private UserController userController;

  @Mock
  private UserService userService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    userController = new UserController(userService);
  }

  @Test
  public void testCreateUser() {
    User user = new User("1", "Alice");

    when(userService.saveUser(any(User.class))).thenReturn(user);

    ResponseEntity<User> response = userController.createUser(user);

    verify(userService, times(1)).saveUser(user);
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(user, response.getBody());
  }

  @Test
  public void testGetUser() {
    User user = new User("1", "Alice");

    when(userService.getUser("1")).thenReturn(user);

    ResponseEntity<User> response = userController.getUser("1");

    verify(userService, times(1)).getUser("1");
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(user, response.getBody());
  }

//  @Test
//  public void testGetUserNotFound() {
//    when(userService.getUser("2")).thenReturn(null);
//
//    Assertions.assertThrows(ResponseStatusException.class, () -> userController.getUser("2"));
//  }
//
//  @Test
//  public void testCreateUserWithJedisException() {
//    User user = new User("1", "Alice");
//
//    when(userService.saveUser(any(User.class))).thenThrow(JedisException.class);
//
//    Assertions.assertThrows(JedisException
//  }
}
