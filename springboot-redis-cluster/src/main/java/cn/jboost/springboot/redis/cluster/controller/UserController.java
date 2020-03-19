package cn.jboost.springboot.redis.cluster.controller;


import cn.jboost.springboot.redis.cluster.entity.User;
import cn.jboost.springboot.redis.cluster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PostMapping("/2")
    public User addUser2(@RequestBody User user){
        return userService.addUser2(user);
    }

    @GetMapping
    public User queryByUsername(@RequestParam("username") String username) {
        return userService.queryByUsername(username);
    }

    @DeleteMapping("/{username}")
    public void deleteByUsername(@PathVariable("username") String username){
        userService.deleteByUsername(username);
    }
}
