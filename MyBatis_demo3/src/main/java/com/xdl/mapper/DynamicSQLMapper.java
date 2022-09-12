package com.xdl.mapper;

import com.xdl.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DynamicSQLMapper {
    /**
     * 根据动态条件查询员工信息
     * @param emp
     * @return
     */
    List<Emp> getEmpByCondition(Emp emp);
    List<Emp> getEmpByConditionWhere(Emp emp);
    List<Emp> getEmpByConditionTrim(Emp emp);
    List<Emp> getEmpByConditionChoose(Emp emp);
    int deleteEmpMoreByIds(@Param("eids") Integer[] eids);
    int insertEmpMoreByList(@Param("emps") List<Emp> emps);
}
