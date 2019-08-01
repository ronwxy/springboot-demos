package cn.jboost.springboot.simpleauth.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/***
 *
 * @Author ronwxy
 * @Date 2019/7/31 14:53   
 */
@Getter
@Setter
@ToString
public class TokenModel {

    //用户id
    private String userId;
    //jwt token
    private String token;

    public TokenModel(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}
