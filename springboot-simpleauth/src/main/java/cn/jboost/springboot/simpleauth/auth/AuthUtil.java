package cn.jboost.springboot.simpleauth.auth;

/***
 *
 * @Author ronwxy
 * @Date 2019/7/31 10:21   
 */
public class AuthUtil {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void setUserId(String userId){
        threadLocal.set(userId);
    }

    public static String getUserId(){
        return threadLocal.get();
    }

    public static void clear(){
        threadLocal.remove();
    }
}
