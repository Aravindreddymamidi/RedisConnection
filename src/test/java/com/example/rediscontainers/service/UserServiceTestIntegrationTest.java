package com.example.rediscontainers.service;

import com.example.rediscontainers.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
class UserServiceTestIntegrationTest {
  private static final String REDIS_IMAGE_NAME = "redis:7.0-alpine";
  private static final int REDIS_PORT = 6379;


  @Autowired
  UserService userService;

  private static GenericContainer<?> redis;

  @BeforeAll
  static void beforeAll() {
    redis = new GenericContainer<>(DockerImageName.parse(REDIS_IMAGE_NAME)).withExposedPorts(REDIS_PORT);
    redis.start();
    setRedisProperties();
  }

  @Test
  void saveUser() {
    User user = userService.saveUser(new User("1", "Tej"));
//    User user1 = userService.saveUser(new User("2", "Tasdasej"));
    User actual = userService.getUser("1");
    Assertions.assertEquals(user, actual);
  }

  @Test
  void getUser() {
  }

  @AfterAll
  static void afterAll() {
    redis.stop();
  }

  private static void setRedisProperties() {
    System.setProperty("spring.data.redis.host", redis.getHost());
    System.setProperty("spring.data.redis.port", redis.getMappedPort(REDIS_PORT).toString());
  }
}