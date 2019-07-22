package cn.jboost.async.event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/***
 * 事件处理类，基于 @EventListener，默认是同步执行的，需要加 @Async 注解
 * @Author ronwxy
 * @Date 2019/7/22 17:56   
 */
@Component
public class MyEventHandler2 {

    @EventListener
    @Async
    public void handle(MyEvent event){
        System.out.println("3. running in thread: " + Thread.currentThread().getName());
        System.out.println("3. arg value: " + event.getArg());
    }
}
