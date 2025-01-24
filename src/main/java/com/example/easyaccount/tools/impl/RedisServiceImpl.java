package com.example.easyaccount.tools.impl;

import com.example.easyaccount.tools.RedisService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;	//Spring自动注入RedisTemplate

    @Override
    public void set(String key, String value) {
        // 20: 表示该数据在缓存中存在的时间，TimeUnit.SECONDS为单位秒，
        // 20秒后缓存中的数据会自动消失
//        redisTemplate.opsForValue().set(key,value,20, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(key,value,60 * 30, TimeUnit.SECONDS);
    }

    @Override
    public String get(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }
}

