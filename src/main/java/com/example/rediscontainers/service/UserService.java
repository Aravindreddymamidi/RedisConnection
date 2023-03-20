package com.example.rediscontainers.service;

import com.example.rediscontainers.model.User;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class UserService {
  private final Jedis jedis;

  public UserService(Jedis jedis) {
    this.jedis = jedis;
  }

  public User saveUser(User user) {
    jedis.set(user.getId(), user.getName());
    return user;
  }

  public User getUser(String id) {
    String name = jedis.get(id);
    if (name != null) {
      User user = new User(id, name);
//      user.setId(id);
//      user.setName(name);
      return user;
    } else {
      return null;
    }
  }

}