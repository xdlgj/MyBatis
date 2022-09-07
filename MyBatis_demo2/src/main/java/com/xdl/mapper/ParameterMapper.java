package com.xdl.mapper;

import com.xdl.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ParameterMapper {

    /**
     * 验证登录（使用@Param注解）
     * @param username
     * @param password
     * @return
     */
    User checkLoginByParam(@Param("username") String username, @Param("password") String password);
    /**
     * 添加用户
     * @param user
     * @return
     */
    int insertUser(User user);
    /**
     * 验证登录
     * @param map 用户名、密码组合成的map
     * @return
     */
    User checkLoginByMap(Map<String, String> map);
    /**
     * 根据用户名和密码验证登录
     * @param username
     * @param password
     * @return
     */
    User checkLogin(String username, String password);
    /**
     * 根据名称查询用户
     * @param username
     * @return
     */
    List<User> getUserByName(String username);
    /**
     * 查询所有用户信息
     */
    List<User> getAllUser();
}
