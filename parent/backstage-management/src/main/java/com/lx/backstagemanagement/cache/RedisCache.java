package com.lx.backstagemanagement.cache;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component("redisCache")
//@PropertySource(value = "classpath:test.properties")
public class RedisCache {
    @Autowired
    @Qualifier(value = "redisTemplate")
    private RedisTemplate redisTemplate;
//    @Value("time")
//    private Long time;

    /**
     * 从Redis缓存获取数据
     *
     * @param redisKey
     * @return
     */
    public Object getDataFromRedis(String redisKey) {
        Object o = redisTemplate.opsForValue().get(redisKey);

        if (o != null) {
            return o;
        }
        return null;
    }

    /**
     * 保存数据到Redis
     *
     * @param key time t
     */
    public void addDataToRedis(String key, Object t) {


        redisTemplate.opsForValue().set(key, JSON.toJSONString(t));
    }


}
