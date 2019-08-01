package cn.jboost.springboot.simpleauth.controller;

import cn.jboost.springboot.simpleauth.auth.RedisTokenManager;
import cn.jboost.springboot.simpleauth.auth.SkipAuth;
import cn.jboost.springboot.simpleauth.util.ApiResponse;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/***
 *
 * @Author ronwxy
 * @Date 2019/8/1 18:10   
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RedisTokenManager tokenManager;

    @SkipAuth
    @RequestMapping("/login")
    public ApiResponse login(@RequestBody Map<String, Object> params) {
        String username = MapUtils.getString(params, "username");
        String password = MapUtils.getString(params, "password");
        if("ksxy".equals(username) && "jboost".equals(password)){
            return ApiResponse.success(tokenManager.createToken(username));
        } else {
            return ApiResponse.failed("用户名或密码错误");
        }
    }
}
