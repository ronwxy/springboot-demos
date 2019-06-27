package cn.jboost.springboot.tkmapper.domain;

import cn.jboost.springboot.autoconfig.tkmapper.domain.AutoIncrementKeyBaseDomain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
@Getter
@Setter
@ToString
public class User extends AutoIncrementKeyBaseDomain<Integer> {
    private String name;
    @ColumnType(jdbcType = JdbcType.CHAR)
    private Gender gender;
    private List<String> favor;
    private Map<String, String> address;

    public enum Gender{
        M,
        F
    }
}
