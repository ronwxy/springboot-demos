package cn.jboost.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootConfigApplication.class, args);
    }

    @Autowired
    private MyService myService;


    @RequestMapping("/hi")
    public String sayHello(@RequestParam String name){
        return  myService.sayHello(name);
    }

    @Autowired
    private MyService2 myService2;

    @RequestMapping("/hi2")
    public String sayHello2(@RequestParam String name){
        return  myService2.sayHello(name);
    }

}
