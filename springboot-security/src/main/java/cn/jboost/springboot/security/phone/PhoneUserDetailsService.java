package cn.jboost.springboot.security.phone;

import cn.jboost.springboot.security.Application;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

/**
 * @Author ronwxy
 * @Date 2020/8/11 8:10
 * @Version 1.0
 */
public class PhoneUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        for(String phone : Application.phoneMap.keySet()) {
            if(phone.equals(username)) {
                return Application.phoneMap.get(phone);
            }
        }
        return new User(username, "123321", new ArrayList<>(0));
    }
}
