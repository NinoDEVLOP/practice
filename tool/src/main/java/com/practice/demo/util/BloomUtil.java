package com.practice.demo.util;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;

public class BloomUtil {

    public static RBloomFilter<String> getBloom(RedissonClient client, String bloomName) {
        RBloomFilter<String> filter = client.getBloomFilter(bloomName);
        if (!filter.isExists()) {
            filter.tryInit(55000000L, 0.0003D);
        }
        return filter;
    }
}
