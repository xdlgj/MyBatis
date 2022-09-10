package com.xdl.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Dept {
    private Integer did;
    private String deptName;
    private List<Emp> emps;
}
