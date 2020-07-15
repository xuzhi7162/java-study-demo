package com.xuzhi.study.redis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;
import java.util.Map;

/**
 * @author: salted_fish
 * @date: 2019/9/16  15:07
 * @projectName: argus
 * @packageName: com.tsit.argus.common.configuration
 * @description:
 */
@Configuration
public class RedisConfiguration {

  /**
   * redisTemplate 序列化使用的jdkSerializeable, 存储二进制字节码, 所以自定义序列化类
   * @param redisConnectionFactory
   * @return
   */
  @Bean
  public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory);
    RedisSerializer<String> stringSerializer = new StringRedisSerializer();
    redisTemplate.setKeySerializer(stringSerializer);
    redisTemplate.setValueSerializer(stringSerializer);
    redisTemplate.setHashKeySerializer(stringSerializer);
    redisTemplate.setHashValueSerializer(stringSerializer);
    return redisTemplate;
  }

  @Bean
  public RedisTemplate<Serializable, Serializable> serializableRedisTemplate(
          RedisConnectionFactory redisConnectionFactory, ObjectMapper objectMapper) {
    GenericJackson2JsonRedisSerializer keySerializer = new GenericJackson2JsonRedisSerializer(
      objectMapper);
    GenericJackson2JsonRedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer(
      objectMapper);

    RedisTemplate<Serializable, Serializable> stringSerializableRedisTemplate = new RedisTemplate<>();
    stringSerializableRedisTemplate.setConnectionFactory(redisConnectionFactory);
    stringSerializableRedisTemplate.setKeySerializer(keySerializer);
    stringSerializableRedisTemplate.setValueSerializer(valueSerializer);

    stringSerializableRedisTemplate.setConnectionFactory(redisConnectionFactory);

    return stringSerializableRedisTemplate;
  }

  @Bean
  public RedisTemplate<String, Serializable> stringSerializableRedisTemplate(
    RedisConnectionFactory redisConnectionFactory) {

    StringRedisSerializer keySerializer = new StringRedisSerializer();
    GenericToStringSerializer<Serializable> valueSerializer = new GenericToStringSerializer<Serializable>(
      Serializable.class);

    RedisTemplate<String, Serializable> stringSerializableRedisTemplate = new RedisTemplate<>();
    stringSerializableRedisTemplate.setConnectionFactory(redisConnectionFactory);
    stringSerializableRedisTemplate.setKeySerializer(keySerializer);
    stringSerializableRedisTemplate.setValueSerializer(valueSerializer);

    stringSerializableRedisTemplate.setConnectionFactory(redisConnectionFactory);

    return stringSerializableRedisTemplate;
  }

  @Bean
  public RedisTemplate<String, Long> stringLongRedisTemplate(
    RedisConnectionFactory redisConnectionFactory) {

    StringRedisSerializer keySerializer = new StringRedisSerializer();
    GenericToStringSerializer<Long> valueSerializer = new GenericToStringSerializer<Long>(
      Long.class);

    RedisTemplate<String, Long> stringSerializableRedisTemplate = new RedisTemplate<>();

    stringSerializableRedisTemplate.setConnectionFactory(redisConnectionFactory);
    stringSerializableRedisTemplate.setKeySerializer(keySerializer);
    stringSerializableRedisTemplate.setValueSerializer(valueSerializer);

    return stringSerializableRedisTemplate;
  }

  @Bean
  public RedisTemplate<String, Integer> stringIntegerRedisTemplate(
    RedisConnectionFactory redisConnectionFactory) {

    StringRedisSerializer keySerializer = new StringRedisSerializer();
    GenericToStringSerializer<Integer> valueSerializer = new GenericToStringSerializer<Integer>(
      Integer.class);

    RedisTemplate<String, Integer> stringSerializableRedisTemplate = new RedisTemplate<>();

    stringSerializableRedisTemplate.setConnectionFactory(redisConnectionFactory);
    stringSerializableRedisTemplate.setKeySerializer(keySerializer);
    stringSerializableRedisTemplate.setValueSerializer(valueSerializer);

    return stringSerializableRedisTemplate;
  }

  @Bean
  public RedisTemplate<String, Map<String, Integer>> stringMapRedisTemplate(
          RedisConnectionFactory redisConnectionFactory, ObjectMapper objectMapper) {

    StringRedisSerializer keySerializer = new StringRedisSerializer();
    GenericJackson2JsonRedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer(
      objectMapper);

    RedisTemplate<String, Map<String, Integer>> stringSerializableRedisTemplate = new RedisTemplate<>();
    stringSerializableRedisTemplate.setConnectionFactory(redisConnectionFactory);
    stringSerializableRedisTemplate.setKeySerializer(keySerializer);
    stringSerializableRedisTemplate.setValueSerializer(valueSerializer);

    stringSerializableRedisTemplate.setConnectionFactory(redisConnectionFactory);

    return stringSerializableRedisTemplate;
  }

  /**
   * key序列化为字符串.value序列化为JSON字符串. key序列化为字符串的原因:key的长度可能是最小的, 有利于Redis性能; 有利于运维.
   * value序列化为JSON字符串的原因:有利于运维.
   */
  @Bean
  public RedisTemplate<String, Object> stringObjectRedisTemplate(
    RedisConnectionFactory redisConnectionFactory,
    ObjectMapper objectMapper) {

    StringRedisSerializer keySerializer = new StringRedisSerializer();
    GenericJackson2JsonRedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer(
      objectMapper);

    RedisTemplate<String, Object> stringSerializableRedisTemplate = new RedisTemplate<>();
    stringSerializableRedisTemplate.setConnectionFactory(redisConnectionFactory);
    stringSerializableRedisTemplate.setKeySerializer(keySerializer);
    stringSerializableRedisTemplate.setValueSerializer(valueSerializer);

    stringSerializableRedisTemplate.setConnectionFactory(redisConnectionFactory);

    return stringSerializableRedisTemplate;
  }
}
