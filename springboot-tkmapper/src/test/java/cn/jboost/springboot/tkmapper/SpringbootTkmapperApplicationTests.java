package cn.jboost.springboot.tkmapper;

import cn.jboost.springboot.tkmapper.domain.User;
import cn.jboost.springboot.tkmapper.service.UserService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTkmapperApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private UserService userService;

    @Test
    public void testTkMapper(){
        User user = new User();
        user.setName("jboost");
        user.setGender(User.Gender.F);
        List<String> favor = Lists.newArrayList();
        favor.add("互联网");
        favor.add("电影");
        favor.add("篮球");
        user.setFavor(favor);
        Map<String, String> address = Maps.newHashMap();
        address.put("province", "湖南");
        address.put("city", "长沙");
        user.setAddress(address);

        userService.create(user);

        Assert.assertNotNull(user.getId());
    }

    @Test
    public void testTransaction(){
        User user = new User();
        user.setName("jboost");
        user.setGender(User.Gender.M);
        try {
            userService.createWithTransaction(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertNotNull(userService.selectByPk(user.getId()));
    }

}
