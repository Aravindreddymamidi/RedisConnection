package com.example.rediscontainers.service;

import static org.junit.jupiter.api.Assertions.*;


import com.example.rediscontainers.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
  private UserService userService;

  @Mock
  private Jedis jedis;

  @BeforeEach
  public void setup() {
//    MockitoAnnotations.openMocks(this);
    userService = new UserService(jedis);
  }

  @Test
  public void testSaveUser() {
    User user = new User("1", "Alice");

//    doNothing().when(jedis).set(anyString(), anyString());
    when(jedis.set(anyString(), anyString())).thenReturn(String.valueOf(user));

    User savedUser = userService.saveUser(user);

    verify(jedis, times(1)).set("1", "Alice");
    Assertions.assertEquals(user, savedUser);
  }

  @Test
  public void testGetUser() {
    User user = new User("1", "Alice");

    when(jedis.get("1")).thenReturn("Alice");

    User retrievedUser = userService.getUser("1");

    verify(jedis, times(1)).get("1");
    Assertions.assertEquals(user, retrievedUser);
  }

  @Test
  public void testGetUserNotFound() {
    when(jedis.get("2")).thenReturn(null);

    User retrievedUser = userService.getUser("2");

    verify(jedis, times(1)).get("2");
    Assertions.assertNull(retrievedUser);
  }

  @Test
  public void testSaveUserWithJedisException() {
    User user = new User("1", "Alice");

    doThrow(JedisException.class).when(jedis).set(anyString(), anyString());

    Assertions.assertThrows(JedisException.class, () -> userService.saveUser(user));
  }

  @Test
  public void testGetUserWithJedisException() {
    doThrow(JedisException.class).when(jedis).get(anyString());

    Assertions.assertThrows(JedisException.class, () -> userService.getUser("1"));
  }
}
