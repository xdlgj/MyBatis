package com.xdl.mapper;

import com.xdl.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpMapper {
    /**
     * 获取所有员工
     * @return
     */
    List<Emp> getAllEmp();

    /**
     * 根据ID获取员工信息包括部门信息
     * @param eid
     * @return
     */
    Emp getEmpAndDeptById(@Param("eid") Integer eid);

    /**
     * 分步获取员工和部门信息，第一步
     * @param eid
     * @return
     */
    Emp getEmpAndDeptByStepOne(@Param("eid") Integer eid);
}
