package cn.jboost.springboot.tkmapper.controller;

import cn.jboost.springboot.autoconfig.mybatisplus.controller.BaseController;
import cn.jboost.springboot.tkmapper.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * BaseController实现了基本的单表接口
 * @Author ronwxy
 * @Date 2019/6/20 18:16   
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User> {

//    @GetMapping("test")
//    public List<User> query(@ModelAttribute User user, Page page){
//
//    }
}
