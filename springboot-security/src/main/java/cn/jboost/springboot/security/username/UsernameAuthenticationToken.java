package cn.jboost.springboot.security.username;

import cn.jboost.springboot.security.util.UserType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * @Author ronwxy
 * @Date 2020/8/10 18:13
 * @Version 1.0
 */
public class UsernameAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private UserType userType;
    private String uuid; //图形验证码uuid
    private String code; //验证码（图形/短信）

    public UsernameAuthenticationToken(Object principal, Object credentials, UserType userType, String uuid, String code) {
        super(principal, credentials);
        this.userType = userType;
        this.uuid = uuid;
        this.code = code;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
