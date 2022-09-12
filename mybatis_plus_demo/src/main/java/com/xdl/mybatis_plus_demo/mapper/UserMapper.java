package com.xdl.mybatis_plus_demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xdl.mybatis_plus_demo.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据ID查询用户信息（返回map集合）
     * @param id
     * @return
     */
    Map<String, Object> getUserMapById(@Param("id") Long id);
}
