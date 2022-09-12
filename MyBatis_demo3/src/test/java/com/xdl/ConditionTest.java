package com.xdl;

import com.xdl.mapper.DynamicSQLMapper;
import com.xdl.pojo.Emp;
import com.xdl.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ConditionTest {
    @Test
    public void testGetEmpByCondition() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> emps = mapper.getEmpByCondition(new Emp(null, "张三", 20, "男", "123@qq.com"));
        System.out.println(emps);
    }
    @Test
    public void testGetEmpByConditionWhere() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> emps = mapper.getEmpByConditionWhere(new Emp(null, "张三", 20, "男", "123@qq.com"));
        System.out.println(emps);
    }
    @Test
    public void testGetEmpByConditionTrim() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> emps = mapper.getEmpByConditionTrim(new Emp(null, "张三", 20, "男", "123@qq.com"));
        System.out.println(emps);
    }
    @Test
    public void testGetEmpByConditionChoose() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> emps = mapper.getEmpByConditionChoose(new Emp(null, "张三", 20, "男", "123@qq.com"));
        System.out.println(emps);
    }

    @Test
    public void testDeleteEmpMoreByIds() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        Integer[] eids = {};
        int ret = mapper.deleteEmpMoreByIds(eids);
        System.out.println(ret);
    }

    @Test
    public void testInsertEmpMoreByList() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        Emp emp1 = new Emp(null, "a1", 18, "男", "123@qq.com");
        Emp emp2 = new Emp(null, "a2", 18, "男", "123@qq.com");
        Emp emp3 = new Emp(null, "a3", 18, "男", "123@qq.com");
        List<Emp> emps = Arrays.asList(emp1, emp2, emp3);
        int ret = mapper.insertEmpMoreByList(emps);
        System.out.println(ret);
    }
}
