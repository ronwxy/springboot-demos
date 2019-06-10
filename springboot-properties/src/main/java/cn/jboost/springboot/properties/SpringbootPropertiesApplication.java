package cn.jboost.springboot.properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class SpringbootPropertiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootPropertiesApplication.class, args);
    }

}
