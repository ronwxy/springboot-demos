package cn.jboost.springboot.firstapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/hello")
public class HelloController {

    @GetMapping
    public String hello(@RequestParam(name = "name")String name){
        return "您好，" + name;
    }
}
