package com.xdl.mapper;

import com.xdl.pojo.User;

import java.util.List;

public interface UserMapper {
    // 添加用户信息
    int insertUser();
    // 更新用户信息
    void updateUser();
    // 删除用户
    void deleteUser();
    // 根据ID查询用户
    User getUserById();
    // 查询所有的用户
    List<User> getAllUser();
}
