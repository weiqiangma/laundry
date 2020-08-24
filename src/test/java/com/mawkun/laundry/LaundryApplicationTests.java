package com.mawkun.laundry;

import cn.pertech.common.cache.CacheService;
import com.mawkun.laundry.base.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class LaundryApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CacheService cacheService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
        User user = new User();
        user.setId(1L);
        user.setUserName("张三");
        user.setMobile("17857026617");
        user.setAddress("宁波市北仑区大碶街道万家公寓");
        redisTemplate.opsForValue().set("user1", user);
    }

    @Test
    void getUser() {
        User user = cacheService.get("user1",User.class);
        System.out.println(user);
    }

}
