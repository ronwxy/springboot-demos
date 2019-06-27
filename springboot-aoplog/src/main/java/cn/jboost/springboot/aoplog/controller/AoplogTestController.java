package cn.jboost.springboot.aoplog.controller;

import com.github.nickvl.xspring.core.log.aop.annotation.LogInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/***
 *
 * @Author ronwxy
 * @Date 2019/6/27 13:51   
 */
@RestController
@RequestMapping("test")
@LogInfo
public class AoplogTestController {

    @GetMapping
    public String test(@RequestParam String user){
        return "Hi " + user;
    }
}
