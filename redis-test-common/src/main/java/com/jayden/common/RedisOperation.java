package com.jayden.common;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisOperation implements ApplicationListener<ApplicationStartedEvent> {
    private final StringRedisTemplate stringRedisTemplate;
    private String key = "key2";

    public RedisOperation(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public void onApplicationEvent(ApplicationStartedEvent event) {
        print();

        stringRedisTemplate.opsForValue().set(key, "value2");

        print();

        clear();

        print();
    }

    private void print() {
        System.out.println();
        System.out.println("keys : " + stringRedisTemplate.keys(key));
        System.out.println("get : " + stringRedisTemplate.opsForValue().get(key));
        System.out.println("hasKey : " + stringRedisTemplate.hasKey(key));
    }

    private void clear() {
        stringRedisTemplate.delete(key);
    }
}
