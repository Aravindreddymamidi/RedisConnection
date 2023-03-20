package com.example.rediscontainers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;


@Configuration
public class RedisConfig {

  @Bean
  public Jedis jedis(RedisProperties redisProperties) {
    return new Jedis(redisProperties.getHost(), redisProperties.getPort());
  }
//
//  public Jedis jedisConnection(RedisProperties redisProperties){
//    return new Jedis(redisProperties.getHost(), redisProperties.getPort());
//  }

//  @Bean
//  public JedisConnectionFactory jedisConnectionFactory(RedisProperties redisProperties) {
//    return new JedisConnectionFactory(
//        new RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort()));
//  }
//
//  @Bean
//  public RedisTemplate<?, ?> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
//    final RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
//    redisTemplate.setConnectionFactory(jedisConnectionFactory);
//    return redisTemplate;
//  }
}