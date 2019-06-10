package cn.jboost.springboot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/***
 * @Desc
 * @Author ronwxy
 * @Date 2019/6/10 17:53   
 */
@Component
@ConfigurationProperties(prefix = "my")
public class MyConfigProperties {
    private String name;
    private String website;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
