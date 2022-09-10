package com.xdl;

import com.xdl.mapper.DeptMapper;
import com.xdl.mapper.EmpMapper;
import com.xdl.pojo.Dept;
import com.xdl.pojo.Emp;
import com.xdl.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class EmpTest {
    @Test
    public void testGetAllEmp() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> allEmp = mapper.getAllEmp();
        allEmp.forEach(emp -> System.out.println(emp));
    }
    @Test
    public void testGetEmpAndDeptById() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = mapper.getEmpAndDeptById(1);
        System.out.println(emp);
    }
    @Test
    public void testGetEmpAndDeptByStep() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = mapper.getEmpAndDeptByStepOne(1);
        System.out.println(emp);
    }

    @Test
    public void testGetDeptEmpByDid() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = mapper.getDeptEmpByDid(1);
        System.out.println(dept);
    }

    @Test
    public void testGetDeptEmpByStep() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = mapper.getDeptAndEmpByStepOne(1);
        System.out.println(dept.getDeptName());
    }
}
