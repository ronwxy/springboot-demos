package cn.jboost.springboot.security;

import cn.jboost.springboot.security.phone.PhoneAuthenticationToken;
import cn.jboost.springboot.security.username.UsernameAuthenticationToken;
import cn.jboost.springboot.security.util.LoginParam;
import cn.jboost.springboot.security.util.LoginType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author ronwxy
 * @Date 2020/8/10 14:44
 * @Version 1.0
 */
public class JboostAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST") || !request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            UsernamePasswordAuthenticationToken authentication = null;
            try {
                LoginParam loginParam = objectMapper.readValue(request.getInputStream(), LoginParam.class);
                if(LoginType.USER.equals(loginParam.getLoginType())){
                    authentication = new UsernameAuthenticationToken(loginParam.getUsername() == null ? "": loginParam.getUsername().trim(),
                            loginParam.getPassword() == null ? "":loginParam.getPassword(),
                            loginParam.getUserType(), loginParam.getUuid(), loginParam.getCode());

                } else if(LoginType.PHONE.equals(loginParam.getLoginType())) {
                    authentication = new PhoneAuthenticationToken(loginParam.getPhone() == null ? "":loginParam.getPhone().trim(),
                            loginParam.getCode(), loginParam.getUserType());
                } else {
                    throw new BadCredentialsException("不支持的登录类型");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.setDetails(request, authentication);
            return this.getAuthenticationManager().authenticate(authentication);
        }
    }
}
