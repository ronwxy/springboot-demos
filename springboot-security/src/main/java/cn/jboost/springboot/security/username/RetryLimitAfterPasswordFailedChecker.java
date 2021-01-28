package cn.jboost.springboot.security.username;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author ronwxy
 * @Date 2020/8/11 10:24
 * @Version 1.0
 */
public class RetryLimitAfterPasswordFailedChecker implements UserDetailsChecker {

    private static final Map<String, Integer> limitMap = new HashMap<>();
    private static final int LIMIT = 3;

    @Override
    public void check(UserDetails toCheck) {
        if(limitMap.containsKey(toCheck.getUsername())) {
            if(limitMap.get(toCheck.getUsername()) == LIMIT) {
                throw new BadCredentialsException("密码重试超过最大次数，请半小时后再试或联系管理员");
            } else {
                limitMap.put(toCheck.getUsername(), limitMap.get(toCheck.getUsername()) + 1);
            }
        } else {
            limitMap.put(toCheck.getUsername(), 1);
        }
    }
}
