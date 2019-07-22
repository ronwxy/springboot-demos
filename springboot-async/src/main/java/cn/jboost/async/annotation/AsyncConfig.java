package cn.jboost.async.annotation;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/***
 *
 * @Author ronwxy
 * @Date 2019/7/22 16:47   
 */
@Configuration
public class AsyncConfig implements AsyncConfigurer {


    @Value("${async.corePoolSize:10}")
    private int corePoolSize;

    @Value("${async.maxPoolSize:200}")
    private int maxPoolSize;

    @Value("${async.queueCapacity:2000}")
    private int queueCapacity;

    @Value("${async.keepAlive:5}")
    private int keepAlive;

    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAlive);
        executor.setThreadNamePrefix("async-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setDaemon(false); //以用户线程模式运行
        executor.initialize();
        return executor;
    }

    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new MyAsyncUncaughtExceptionHandler();
    }

    public static class MyAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {

        public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
            System.out.println("catch exception when invoke " + method.getName());
            throwable.printStackTrace();
        }
    }
}
