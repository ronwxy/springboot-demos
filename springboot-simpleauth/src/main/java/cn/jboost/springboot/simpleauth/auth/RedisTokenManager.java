package cn.jboost.springboot.simpleauth.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/***
 *
 * @Author ronwxy
 * @Date 2019/7/31 14:47   
 */
@Component
public class RedisTokenManager {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 生成TOKEN
     */
    public String createToken(String userId) {
        //使用uuid作为源token
        String token = Jwts.builder().setId(userId).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, JwtConstant.JWT_SECRET).compact();
        //存储到redis并设置过期时间
        redisTemplate.boundValueOps(JwtConstant.AUTHORIZATION + ":" + userId).set(token, JwtConstant.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return token;
    }

    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        String token = redisTemplate.boundValueOps(JwtConstant.AUTHORIZATION + ":" + model.getUserId()).get();
        if (token == null || !token.equals(model.getToken())) {
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        redisTemplate.boundValueOps(model.getUserId()).expire(JwtConstant.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return true;
    }

    public void deleteToken(String userId) {
        redisTemplate.delete(userId);
    }

}

