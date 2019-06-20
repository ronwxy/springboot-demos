package cn.jboost.springboot.tkmapper.controller;

import cn.jboost.springboot.autoconfig.web.controller.BaseController;
import cn.jboost.springboot.tkmapper.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 *
 * @Author ronwxy
 * @Date 2019/6/20 18:16   
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<Integer, User> {
}
