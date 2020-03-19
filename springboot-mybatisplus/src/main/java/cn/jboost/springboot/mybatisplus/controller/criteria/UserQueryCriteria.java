package cn.jboost.springboot.mybatisplus.controller.criteria;

import cn.jboost.springboot.common.annotation.Query;
import lombok.Data;

import java.util.List;

@Data
public class UserQueryCriteria {

    @Query(type = Query.Type.BETWEEN, propName = "age")
    private Integer[] ageRange;

    @Query(type = Query.Type.LIKE)
    private String name;

    @Query(type = Query.Type.IN, propName = "id")
    private List<Long> ids;
}
