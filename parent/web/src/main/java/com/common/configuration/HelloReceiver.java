package com.common.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;


public class HelloReceiver {
    @Autowired
    private RedisTemplate redisTemplate;


    public void foo(String hello) {
        System.out.println(hello);
        if (hello.contains("category")) {
            redisTemplate.opsForValue().set("goodsCateGory", null);
        } else if (hello.contains("product")) {
            redisTemplate.opsForValue().set("hotProduct", null);
            redisTemplate.opsForValue().set("newProductList", null);
        }
    }


}