package cn.jboost.springboot.redis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.time.LocalDateTime;

/**
 * @Author ronwxy
 * @Date 2021/1/28 9:58
 * @Version 1.0
 */
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 针对redis数据失效事件，进行数据处理
     *
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String expiredKey = message.toString();
        System.out.printf(LocalDateTime.now().toString());
        System.out.println("expire key:" + expiredKey);
    }

}
