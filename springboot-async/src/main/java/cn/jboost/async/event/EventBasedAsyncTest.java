package cn.jboost.async.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/***
 *
 * @Author ronwxy
 * @Date 2019/7/22 17:40   
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EventBasedAsyncTest {

    @Autowired
    private MyEventPublisher myEventPublisher;

    @Test
    public void testAsync() throws InterruptedException {
        System.out.println("1. running in thread: " + Thread.currentThread().getName());
        myEventPublisher.publishEvent(new MyEvent(this,"testing event based async"));
        Thread.sleep(3);
    }
}
