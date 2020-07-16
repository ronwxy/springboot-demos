package cn.jboost.springboot.limiter;


import cn.jboost.springboot.autoconfig.web.Swagger2AutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {Swagger2AutoConfiguration.class})
public class LimiterApplication {
    public static void main(String[] args) {
        SpringApplication.run(LimiterApplication.class, args);
    }
}
