package com.xdl;

import com.xdl.mapper.EmpMapper;
import com.xdl.pojo.Emp;
import com.xdl.utils.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CacheTest {
    @Test
    public void testCacheOne() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> emps1 = mapper.getAllEmp();
        System.out.println(emps1);
        sqlSession.clearCache(); // 清空sqlSession缓存会使一级缓存失效
        List<Emp> emps2 = mapper.getAllEmp();
        System.out.println(emps2);
    }

    /**
     * 测试二级缓存
     */
    @Test
    public void testCacheTwo() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
            SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
            EmpMapper mapper1 = sqlSession1.getMapper(EmpMapper.class);
            List<Emp> emps1 = mapper1.getAllEmp();
            System.out.println(emps1);
            // 只有当提交或关闭当前session时，才会把缓存存入二级缓存中
            sqlSession1.close();
            System.out.println("============================");
            SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
            EmpMapper mapper2 = sqlSession2.getMapper(EmpMapper.class);
            List<Emp> emps2 = mapper2.getAllEmp();
            System.out.println(emps2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
