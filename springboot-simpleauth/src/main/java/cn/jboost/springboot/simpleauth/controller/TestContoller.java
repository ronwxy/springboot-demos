package cn.jboost.springboot.simpleauth.controller;

import cn.jboost.springboot.simpleauth.auth.AuthUtil;
import cn.jboost.springboot.simpleauth.auth.SkipAuth;
import cn.jboost.springboot.simpleauth.util.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 *
 * @Author ronwxy
 * @Date 2019/8/1 18:49   
 */

@RestController
@RequestMapping("/test")
public class TestContoller {

    @SkipAuth
    @RequestMapping("/skip-auth")
    public ApiResponse skipAuth() {
       return ApiResponse.success("不需要认证的接口调用");
    }

    @RequestMapping("/need-auth")
    public ApiResponse needAuth() {
        return ApiResponse.success("username: " + AuthUtil.getUserId());
    }
}
