package cn.jboost.springboot.tkmapper.service;

import cn.jboost.springboot.autoconfig.tkmapper.service.BaseService;
import cn.jboost.springboot.tkmapper.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/***
 * @Desc
 * @Author ronwxy
 * @Date 2019/6/20 8:59   
 */
@Service
public class UserService extends BaseService<Integer, User> {

    @Transactional
    public void createWithTransaction(User user){
        create(user);
        //用于测试事务
        throw new RuntimeException("抛出异常，让前面的数据库操作回滚");
    }
}
