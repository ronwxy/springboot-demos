package cn.jboost.springboot.error.controller;


import cn.jboost.springboot.common.exception.ExceptionUtil;
import cn.jboost.springboot.error.util.ErrorCodeEnum;
import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/***
 *
 * @Author ronwxy
 * @Date 2019/7/3 13:04   
 */
@RestController
@RequestMapping("exception")
public class ExceptionTestController {

    /**
    * 不catch具体异常，主动抛出一个异常交给统一异常处理
    * @param
    * @return
    */
    @GetMapping("client")
    public void clientSideException(){
        ExceptionUtil.rethrowClientSideException(ErrorCodeEnum.authorizer_notexist.getMsg());
    }

    /**
    * catch具体异常，然后rethrow交给统一异常处理
    * @param
    * @return
    */
    @GetMapping("server")
    public void serverSideException(){
        try {
            throw new RuntimeException("运行时异常");
        } catch (Exception ex) {
            //这里不需要打日志，会统一在异常处理里记录日志
            ExceptionUtil.rethrowServerSideException(ErrorCodeEnum.internal_error.getMsg(), ex);
        }
    }

    /**
     * 正常返回
     * @param
     * @return
     */
    @GetMapping("normal")
    public Map<String, Object> normal(){
        Map<String, Object> result = Maps.newLinkedHashMap();
        result.put("name","jboost");
        result.put("website","blog.jboost.cn");
        result.put("wechat","jboost-ksxy");
        return result;
    }
}
