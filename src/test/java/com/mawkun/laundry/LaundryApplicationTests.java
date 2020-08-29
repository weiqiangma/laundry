package com.mawkun.laundry;

import cn.pertech.common.cache.CacheService;
import com.mawkun.laundry.base.entity.User;
import com.mawkun.laundry.utils.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class LaundryApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

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
        String img = "c1d3a7de1cef4812bb8ba41c27e1fae11598696669854.jpeg,d4485bac3a124aaa84a6a73d30908d181598696669855.jpeg,0fbed345878f46baa8b3ac2a029201131598696669866.jpeg";
        String delImages = "c1d3a7de1cef4812bb8ba41c27e1fae11598696669854.jpeg,d4485bac3a124aaa84a6a73d30908d181598696669855.jpeg";
        String newImg = StringUtils.remove(img, delImages);
        System.out.println(newImg);
    }

}
