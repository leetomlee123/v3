package com.lx.backstagemanagement.dao;

import com.alibaba.fastjson.JSON;
import com.lx.backstagemanagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class UserRedis {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public RedisTemplate<String, String> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void add(String key, Long time, User user) {

        redisTemplate.opsForValue().set(key, JSON.toJSONString(user), time, TimeUnit.MINUTES);
    }
}
