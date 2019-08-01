package cn.jboost.springboot.simpleauth.config;

import cn.jboost.springboot.simpleauth.auth.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/***
 *
 * @Author ronwxy
 * @Date 2019/7/31 10:17   
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private AuthInterceptor authInterceptor;

    @Autowired
    public void setAuthInterceptor(AuthInterceptor authInterceptor){
        this.authInterceptor = authInterceptor;
    }
    /**
    * 注册鉴权拦截器
    * @param
    * @return
    */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/**").excludePathPatterns("/error");
    }
}
