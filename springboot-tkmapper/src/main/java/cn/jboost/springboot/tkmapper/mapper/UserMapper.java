package cn.jboost.springboot.tkmapper.mapper;

import cn.jboost.springboot.autoconfig.tkmapper.mapper.BaseMapper;
import cn.jboost.springboot.tkmapper.domain.User;
import org.springframework.stereotype.Repository;

/***
 * @Desc DAO bean， 可以使用{@code org.springframework.stereotype.Repository} 或 {@code org.apache.ibatis.annotations.Mapper}
 * @Author ronwxy
 * @Date 2019/6/20 8:54   
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
