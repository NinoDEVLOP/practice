package com.practice.demo.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class LocalCacheConfiguration {

    @Bean("localNonceCache")
    public Cache<Object, Object> localNonceCache() {
        CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder();
        //并发等级涉及到guava中类ConcurrentHashMap的segments的个数，等级越高，segments的个数越多
        return builder
                .concurrencyLevel(2)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(2048)
                .softValues().weakKeys().build();
    }


}
