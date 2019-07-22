package cn.jboost.async.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/***
 *
 * @Author ronwxy
 * @Date 2019/7/22 17:09   
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AnnotationBasedAsyncTest {

    @Autowired
    private AsyncService asyncService;

    @Test
    public void testAsync() throws InterruptedException {
        System.out.println("1. running in thread: " + Thread.currentThread().getName());
        asyncService.asyncMethod();

        Thread.sleep(3);
    }

    @Test
    public void testAysncWithException() throws InterruptedException {
        System.out.println("1. running in thread: " + Thread.currentThread().getName());
        asyncService.asyncMethodWithException();

        Thread.sleep(3);
    }
}
