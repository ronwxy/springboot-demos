package cn.jboost.springboot.tkmapper.domain;

import cn.jboost.springboot.autoconfig.tkmapper.domain.FixedIdBaseDomain;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Table;
import java.util.List;
import java.util.Map;

/***
 * @Desc
 * @Author ronwxy
 * @Date 2019/6/20 8:55   
 */
@Table(name = "user")
public class User extends FixedIdBaseDomain<Integer> {
    private String name;
    @ColumnType(jdbcType = JdbcType.CHAR)
    private Gender gender;
    private List<String> favor;
    private Map<String, String> address;

    public enum Gender{
        M,
        F;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<String> getFavor() {
        return favor;
    }

    public void setFavor(List<String> favor) {
        this.favor = favor;
    }

    public Map<String, String> getAddress() {
        return address;
    }

    public void setAddress(Map<String, String> address) {
        this.address = address;
    }
}
