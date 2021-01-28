package cn.jboost.springboot.security.phone;

import cn.jboost.springboot.security.Application;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Author ronwxy
 * @Date 2020/8/10 16:04
 * @Version 1.0
 */
public class PhoneAuthenticationProvider extends DaoAuthenticationProvider {


    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

        if(!"123456".equals(authentication.getCredentials().toString())) {
            throw new BadCredentialsException("验证码错误");
        }
        if(! Application.phoneMap.containsKey(userDetails.getUsername())) {
            Application.phoneMap.put(userDetails.getUsername(), (User) userDetails);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PhoneAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
