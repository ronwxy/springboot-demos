package cn.jboost.async.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/***
 * 事件发布类
 * @Author ronwxy
 * @Date 2019/7/22 17:37   
 */
@Component
public class MyEventPublisher implements ApplicationEventPublisherAware {

    protected ApplicationEventPublisher applicationEventPublisher;

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishEvent(ApplicationEvent event){
        this.applicationEventPublisher.publishEvent(event);
    }
}
