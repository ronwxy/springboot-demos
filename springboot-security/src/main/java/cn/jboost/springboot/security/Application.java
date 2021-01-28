package cn.jboost.springboot.security;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author ronwxy
 * @Date 2020/8/10 14:17
 * @Version 1.0
 */
@SpringBootApplication
public class Application {

    public static final Map<String, User> usernameMap = new HashMap<>();
    public static final Map<String, User> phoneMap = new HashMap<>();

    public static void main(String[] args) {
        usernameMap.put("test", new User("test","$2a$10$e5TdbQpb9X6i8VySWpH02.5bARx4At5APp5.XlPOyYY1daFQ2i3kK", new ArrayList<>(0)));
        usernameMap.put("jboost", new User("jboost","$2a$10$e5TdbQpb9X6i8VySWpH02.5bARx4At5APp5.XlPOyYY1daFQ2i3kK", new ArrayList<>(0)));
        SpringApplication.run(Application.class, args);
    }
}
