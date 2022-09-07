package com.xdl;

import com.xdl.mapper.ParameterMapper;
import com.xdl.pojo.User;
import com.xdl.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class ParameterTest {
    @Test
    public void TestGetAllUser() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ParameterMapper parameterMapper = sqlSession.getMapper(ParameterMapper.class);
        List<User> users = parameterMapper.getAllUser();
        users.forEach(user -> System.out.println(user));
    }
    @Test
    public void TestGetUserByName() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ParameterMapper parameterMapper = sqlSession.getMapper(ParameterMapper.class);
        List<User> users = parameterMapper.getUserByName("张三");
        users.forEach(user -> System.out.println(user));
    }
    @Test
    public void TestCheckLogin() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ParameterMapper parameterMapper = sqlSession.getMapper(ParameterMapper.class);
        User user = parameterMapper.checkLogin("李四 ", "123");
        System.out.println(user);
    }
    @Test
    public void TestCheckLoginByMap() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ParameterMapper parameterMapper = sqlSession.getMapper(ParameterMapper.class);
        HashMap<String, String> userMap = new HashMap<>();
        userMap.put("username", "李四");
        userMap.put("password", "123");
        User user = parameterMapper.checkLoginByMap(userMap);
        System.out.println(user);
    }
    @Test
    public void TestInsertUser() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ParameterMapper parameterMapper = sqlSession.getMapper(ParameterMapper.class);
        User user = new User(null, "王五", "1234", 18, "男", "123@qq.com");
        int ret = parameterMapper.insertUser(user);
        System.out.println(ret);
    }
    @Test
    public void TestCheckLoginByParam() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ParameterMapper parameterMapper = sqlSession.getMapper(ParameterMapper.class);
        User user = parameterMapper.checkLoginByParam("李四", "123");
        System.out.println(user);
    }
}
