package cn.jboost.springboot.mybatisplus.controller;

import cn.jboost.springboot.mybatisplus.controller.criteria.UserQueryCriteria;
import cn.jboost.springboot.mybatisplus.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User, UserQueryCriteria> {

}
