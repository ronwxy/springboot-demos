package cn.jboost.springboot.simpleauth.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * 在Controller的方法上使用此注解，不会检查用户是否登录
 * 未使用此注解的，都会进行登录鉴权
 * @Author ronwxy
 * @Date 2019/7/31 10:08   
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SkipAuth {
}
