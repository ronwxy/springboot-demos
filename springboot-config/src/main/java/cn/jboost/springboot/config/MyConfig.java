package cn.jboost.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/***
 * @Desc Java配置类
 * @Author ronwxy
 * @Date 2019/6/11 16:04   
 */
@Configuration
@ImportResource("spring.xml")  //引入基于xml的配置
public class MyConfig {

    @Bean
    public MyService myService(){
        return new MyService();
    }
}
