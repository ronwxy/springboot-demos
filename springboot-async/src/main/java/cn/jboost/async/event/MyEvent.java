package cn.jboost.async.event;

import org.springframework.context.ApplicationEvent;

/***
 * 事件类
 * @Author ronwxy
 * @Date 2019/7/22 17:28   
 */
public class MyEvent extends ApplicationEvent {

    private String arg;

    public MyEvent(Object source, String arg) {
        super(source);
        this.arg = arg;
    }

    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }
}
