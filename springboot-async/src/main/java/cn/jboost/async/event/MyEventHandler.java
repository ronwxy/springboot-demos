package cn.jboost.async.event;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/***
 *  事件处理类，默认是同步执行的，需要加 @Async 注解
 * @Author ronwxy
 * @Date 2019/7/22 17:30   
 */
@Component
@Async
public class MyEventHandler implements ApplicationListener<MyEvent> {

    public void onApplicationEvent(MyEvent event) {
        System.out.println("2. running in thread: " + Thread.currentThread().getName());
        System.out.println("2. arg value: " + event.getArg());
    }
}
