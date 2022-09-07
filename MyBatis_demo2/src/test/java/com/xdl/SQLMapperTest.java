package com.xdl;

import com.xdl.mapper.SQLMapper;
import com.xdl.pojo.User;
import com.xdl.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class SQLMapperTest {
    @Test
    public void testGetUserByLike() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
        List<User> users = mapper.getUserByLike("张");
        System.out.println(users);
    }
    @Test
    public void testDeleteMore() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
        int result = mapper.deleteMore("1,2,4,5,6");
        System.out.println(result);
    }
    @Test
    public void testGetUserByTableName() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
        List<User> users = mapper.getUserByTableName("t_user");
        System.out.println(users);
    }
    @Test
    public void testInsertUser() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
        User user = new User(null, "赵六", "123", 20, "男", "123@qq.com");
        int i = mapper.insertUser(user);
        // 保存之前user的id为null 保存之后为实际的id
        System.out.println(user);
    }
}
