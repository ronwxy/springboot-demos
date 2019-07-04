package cn.jboost.springboot.swagger;

import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/***
 *
 * @Author ronwxy
 * @Date 2019/6/27 18:09   
 */
@RestController
@RequestMapping("swagger")
@Api(value = "测试接口",tags = "测试tags")
public class SwaggerController {

    @GetMapping
    @ApiOperation(value = "GET请求",notes = "Get请求notes")
    public Map<String, Object> get(@RequestParam String user){
        Map<String, Object> map = Maps.newHashMap();
        map.put("name", "Hi " + user);
        map.put("website", "blog.jboost.cn");
        return map;
    }

    @PostMapping
    @ApiOperation(value = "POST请求",notes = "POST请求notes")
    public Map<String, Object> post(@RequestBody Map<String, Object> map){
       return map;
    }
}
