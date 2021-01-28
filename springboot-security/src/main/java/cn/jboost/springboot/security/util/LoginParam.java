package cn.jboost.springboot.security.util;

import java.io.Serializable;

/**
 * @Author ronwxy
 * @Date 2020/8/10 17:51
 * @Version 1.0
 */
public class LoginParam implements Serializable {

    private LoginType loginType;
    private UserType userType; //用户类型
    private String username; //用户名
    private String password; //密码
    private String uuid; //图形验证码uuid
    private String code; //验证码（图形/短信）
    private String phone; //手机号码

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
