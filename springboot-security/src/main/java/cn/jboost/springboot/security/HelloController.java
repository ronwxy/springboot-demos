package cn.jboost.springboot.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ronwxy
 * @Date 2020/8/10 15:01
 * @Version 1.0
 */
@RestController
public class HelloController {

    @GetMapping("/users")
    public List<User> hello() {
        List<User> users = new ArrayList<>();
        users.addAll(Application.usernameMap.values());
        users.addAll(Application.phoneMap.values());
        return users;
    }
}
