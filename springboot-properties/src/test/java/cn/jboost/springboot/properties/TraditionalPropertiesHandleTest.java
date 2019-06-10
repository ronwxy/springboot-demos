package cn.jboost.springboot.properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/***
 * @Desc 传统自定义属性获取测试
 * @Author ronwxy
 * @Date 2019/6/10 17:42   
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TraditionalPropertiesHandleTest {

    @Test
    public void test(){
        System.out.println(MyPropertiesUtil.getValue("my.name"));
        System.out.println(MyPropertiesUtil.getValue("my.website"));
    }

}
