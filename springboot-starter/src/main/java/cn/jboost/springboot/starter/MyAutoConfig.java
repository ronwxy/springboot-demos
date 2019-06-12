package cn.jboost.springboot.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MyProperties.class)
public class MyAutoConfig {

    @Autowired
    private MyProperties myProperties;

    @Bean
    @ConditionalOnProperty(prefix = "my", name = "disable", havingValue = "false")
    public MyService myService(){
        return new MyService("Hi " + myProperties.getName() + ", welcome to visit " + myProperties.getWebsite());
    }
}
