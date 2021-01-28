package cn.jboost.springboot.security.phone;

import cn.jboost.springboot.security.util.UserType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * @Author ronwxy
 * @Date 2020/8/10 18:12
 * @Version 1.0
 */
public class PhoneAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private UserType userType;

    public PhoneAuthenticationToken(Object principal, Object credentials, UserType userType) {
        super(principal, credentials);
        this.userType = userType;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
