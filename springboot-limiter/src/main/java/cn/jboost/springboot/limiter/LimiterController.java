package cn.jboost.springboot.limiter;

import cn.jboost.springboot.autoconfig.limiter.*;
import cn.jboost.springboot.common.exception.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @Author ronwxy
 * @Date 2020/7/15 17:22
 * @Version 1.0
 */
@RestController
@RequestMapping("limiter")
public class LimiterController {

    @Autowired
    private RedisRateLimiterFactory redisRateLimiterFactory;

    /**
     * 注解形式
     * @param key
     * @return
     */
    @GetMapping("/count")
    @CountLimit(key = "#{key}", limit = 2, period = 10, limitType = LimitType.CUSTOM)
    public String testCountLimit(@RequestParam("key") String key){
        return "test count limiter...";
    }

    /**
     * 注解形式
     * @param key
     * @return
     */
    @GetMapping("/rate")
    @RateLimit(rate = 1.0/5, burst = 5.0, expire = 120, timeout = 1)
    public String testRateLimit(@RequestParam("key") String key){
        return "test rate limiter...";
    }

    /**
     * 代码段形式
     * @param
     * @return
     */
    @GetMapping("/rate2")
    public String testRateLimit(){
        RedisRateLimiter limiter = redisRateLimiterFactory.build("LimiterController.testRateLimit", 1.0/30, 30, 120);
        if(!limiter.tryAcquire("app.limiter", 0, TimeUnit.SECONDS)) {
            System.out.println(LocalDateTime.now());
            ExceptionUtil.rethrowClientSideException("您的访问过于频繁，请稍后重试");
        }
        return "test rate limiter 2...";
    }


}
