package cn.jboost.springboot.error.util;

import cn.jboost.springboot.autoconfig.error.BaseErrors;

/***
 *
 * @Author ronwxy
 * @Date 2019/7/3 13:04   
 */
public enum ErrorCodeEnum implements BaseErrors {

    qrcode_existed("该公众号下已存在同名二维码"),
    authorizer_notexist("公众号不存在"),
    internal_error("抱歉，服务出错了，请稍后重试"),
    gotoauth_fail("跳转授权页面失败");

    private String msg;

    private ErrorCodeEnum(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return name();
    }

    public String getMsg() {
        return msg;
    }
}
