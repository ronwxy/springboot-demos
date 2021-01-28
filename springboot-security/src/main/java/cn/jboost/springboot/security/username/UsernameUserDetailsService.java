package cn.jboost.springboot.security.username;

import cn.jboost.springboot.security.Application;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @Author ronwxy
 * @Date 2020/5/20 11:41
 * @Version 1.0
 */
public class UsernameUserDetailsService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        for(String uname: Application.usernameMap.keySet()) {
            if(uname.equals(username)){
                return Application.usernameMap.get(uname);
            }
        }
        return null;
    }
}
