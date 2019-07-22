package cn.jboost.async.annotation;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/***
 *
 * @Author ronwxy
 * @Date 2019/7/22 17:00   
 */
@Service
public class AsyncService {

    @Async
    public void asyncMethod(){
        System.out.println("2. running in thread: " + Thread.currentThread().getName());
    }

    @Async
    public void asyncMethodWithException() {
        throw new RuntimeException("exception in async method");
    }
}
