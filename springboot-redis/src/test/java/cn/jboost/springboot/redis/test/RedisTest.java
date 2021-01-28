package cn.jboost.springboot.redis.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @Author ronwxy
 * @Date 2021/1/28 9:12
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testExpire() throws InterruptedException {
        System.out.printf(LocalDateTime.now().toString());
        redisTemplate.boundValueOps("test.key").set("test-expire");
        redisTemplate.boundValueOps("test.key").expire(5, TimeUnit.SECONDS);
        Thread.sleep(10000);
    }

}
