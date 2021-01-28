package cn.jboost.springboot.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @Author ronwxy
 * @Date 2021/1/28 9:14
 * @Version 1.0
 */
@Configuration
public class RedisListenerConfig {

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }

    @Bean
    public RedisKeyExpirationListener keyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        return new RedisKeyExpirationListener(listenerContainer);
    }
}
