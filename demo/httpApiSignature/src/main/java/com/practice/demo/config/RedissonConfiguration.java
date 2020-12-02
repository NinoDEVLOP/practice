package com.practice.demo.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.HyperLogLogOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedissonConfiguration {

    private static final Logger log = LoggerFactory.getLogger(RedissonConfiguration.class);

    @Bean
    public ValueOperations<Object, Object> valueOperations(RedisConnectionFactory redisConnectionFactory) {
        return redisTemplate(redisConnectionFactory).opsForValue();
    }

    @Bean
    public ListOperations<Object, Object> listOperations(RedisConnectionFactory redisConnectionFactory) {
        return redisTemplate(redisConnectionFactory).opsForList();
    }

    @Bean
    public SetOperations<Object, Object> setOperations(RedisConnectionFactory redisConnectionFactory) {
        return redisTemplate(redisConnectionFactory).opsForSet();
    }

    @Bean
    public ZSetOperations<Object, Object> zSetOperations(RedisConnectionFactory redisConnectionFactory) {
        return redisTemplate(redisConnectionFactory).opsForZSet();
    }

    @Bean
    public HashOperations<Object, Object, Object> hashOperations(RedisConnectionFactory redisConnectionFactory) {
        return redisTemplate(redisConnectionFactory).opsForHash();
    }

    @Bean
    public HyperLogLogOperations<Object, Object> hyperLogLogOperations(RedisConnectionFactory redisConnectionFactory) {
        return redisTemplate(redisConnectionFactory).opsForHyperLogLog();
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(name = "{redisTemplate}")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        log.info("Initialization Redis RedisTemplate");
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        ObjectMapper mapper = new ObjectMapper().
                registerModule(new ParameterNamesModule()).
                registerModule(new Jdk8Module()).
                registerModule(new JavaTimeModule());
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        // mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,ObjectMapper.DefaultTyping.NON_FINAL);
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer(mapper);

        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(genericJackson2JsonRedisSerializer);
        template.setHashValueSerializer(genericJackson2JsonRedisSerializer);
        template.setConnectionFactory(redisConnectionFactory);
        template.afterPropertiesSet();
        return template;
    }

}
