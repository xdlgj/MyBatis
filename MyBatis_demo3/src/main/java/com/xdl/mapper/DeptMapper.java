package com.xdl.mapper;

import com.xdl.pojo.Dept;
import org.apache.ibatis.annotations.Param;

public interface DeptMapper {
    /**
     * 第二步
     * @param did
     * @return
     */
    Dept getEmpAndDeptByStepTwo(@Param("did") Integer did);

    /**
     * 根据部门id查新部门以及部门中的员工信息
     * @param did
     * @return
     */
    Dept getDeptEmpByDid(@Param("did") int did);

    /**
     * 分步获取部门以及部门员工的信息, 第一步
     * @param did
     * @return
     */
    Dept getDeptAndEmpByStepOne(@Param("did") int did);

}
