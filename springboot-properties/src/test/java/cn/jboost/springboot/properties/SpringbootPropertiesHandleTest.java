package cn.jboost.springboot.properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

/***
 * @Desc Springboot 访问自定义属性
 * @Author ronwxy
 * @Date 2019/6/10 17:53   
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootPropertiesHandleTest {

    @Autowired
    private MyConfigProperties myConfigProperties;

    @Test
    public void testConfigurationProperties(){
        System.out.println("test @ConfigurationProperties ==========");
        System.out.println(myConfigProperties.getName());
        System.out.println(myConfigProperties.getWebsite());
    }

    @Value("${my.name}")
    private String name;
    @Value("${my.website}")
    private String website;

    @Test
    public void testValue(){
        System.out.println("test @Value ==========");
        System.out.println(name);
        System.out.println(website);
    }

    @Autowired
    private Environment env;

    @Test
    public void testEnvironment(){
        System.out.println("test Environment ==========");
        System.out.println(env.getProperty("my.name"));
        System.out.println(env.getProperty("my.website", "default value"));
    }
}
