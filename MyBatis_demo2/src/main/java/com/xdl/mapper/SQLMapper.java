package com.xdl.mapper;

import com.xdl.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SQLMapper {
    /**
     * 添加用户，并获取到自增长的id
     * @param user
     * @return
     */
    int insertUser(User user);
    /**
     * 查询执行表名的数据
     */
    List<User> getUserByTableName(@Param("tableName") String tableName);
    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteMore(@Param("ids") String ids);
    /**
     * 根据用户名称进行模糊查询
     */
    List<User> getUserByLike(@Param("username") String username);
}
