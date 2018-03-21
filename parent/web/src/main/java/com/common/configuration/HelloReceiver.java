package com.common.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;


public class HelloReceiver {
    @Autowired
    private RedisTemplate redisTemplate;


    public void foo(String hello) {
        System.out.println(hello);
        if (hello.contains("category")) {
            redisTemplate.delete("goodsCateGory");
        } else if (hello.contains("product")) {
            redisTemplate.delete("hotProduct");
            redisTemplate.delete("newProductList");
        }
    }


}