package cn.jboost.springboot.starter;

public class MyService {
    private String hiStr;

    public MyService(String hiStr){
        this.hiStr = hiStr;
    }

    public String sayHi(){
        return this.hiStr;
    }
}
