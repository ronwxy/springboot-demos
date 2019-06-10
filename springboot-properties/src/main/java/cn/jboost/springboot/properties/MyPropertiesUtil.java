package cn.jboost.springboot.properties;

import java.util.Properties;

/***
 * @Desc 传统自定义属性读取工具类
 * @Author ronwxy
 * @Date 2019/6/10 17:37   
 */
public class MyPropertiesUtil {

    private static Properties properties;

    public static void init(Properties props) {
        properties = props;
    }

    public static String getValue(String key) {
        return properties.getProperty(key);
    }
}
