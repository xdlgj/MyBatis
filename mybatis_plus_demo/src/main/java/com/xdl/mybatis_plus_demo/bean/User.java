package com.xdl.mybatis_plus_demo.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.xdl.mybatis_plus_demo.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value="t_user")
public class User {
    @TableId(value = "uid", type = IdType.AUTO) // 如果使用自增长，则数据库必须设置自增长属性
    private Long id;
    @TableField("user_name")
    private String name;
    private Integer age;
    private String email;
    @TableLogic
    private Integer isDeleted;
    private SexEnum sex;
}
