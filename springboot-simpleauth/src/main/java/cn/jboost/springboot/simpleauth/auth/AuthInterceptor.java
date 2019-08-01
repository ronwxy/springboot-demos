package cn.jboost.springboot.simpleauth.auth;

import cn.jboost.springboot.simpleauth.util.ApiResponse;
import cn.jboost.springboot.simpleauth.util.WebUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/***
 *
 * @Author ronwxy
 * @Date 2019/7/31 10:14   
 */
@Component
@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisTokenManager tokenManager;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String requestPath = request.getRequestURI().substring(request.getContextPath().length());
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 如果方法注明了 SkipAuth，则不需要登录token验证
        if (method.getAnnotation(SkipAuth.class) != null) {
            return true;
        }

        // 从header中得到token
        String authorization = request.getHeader(JwtConstant.AUTHORIZATION);
        // 验证token
        if(StringUtils.isBlank(authorization)){
            WebUtil.outputJsonString(ApiResponse.failed("未提供有效Token！"), response);
            return false;
        }
        try {
            Claims claims = Jwts.parser().setSigningKey(JwtConstant.JWT_SECRET).parseClaimsJws(authorization).getBody();
            String userId = claims.getId();
            TokenModel model = new TokenModel(userId, authorization);
            if (tokenManager.checkToken(model)) {
                // 通过ThreadLocal设置下游需要访问的值
                AuthUtil.setUserId(model.getUserId());
                return true;
            } else {
                log.info("连接" + requestPath + "拒绝");
                WebUtil.outputJsonString(ApiResponse.failed("未提供有效Token！"), response);
                return false;
            }
        } catch (Exception e) {
            log.error("连接" + requestPath + "发生错误:", e);
            WebUtil.outputJsonString(ApiResponse.failed("校验Token发生异常！"), response);
            return false;
        }
    }


    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //结束后清除，否则由于连接池复用，导致ThreadLocal的值被其他用户获取
        AuthUtil.clear();
    }

}
