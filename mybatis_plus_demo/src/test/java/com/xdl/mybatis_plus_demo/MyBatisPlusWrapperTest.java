package com.xdl.mybatis_plus_demo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.xdl.mybatis_plus_demo.bean.User;
import com.xdl.mybatis_plus_demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class MyBatisPlusWrapperTest {

    @Autowired
    private UserMapper userMapper;
    @Test
    public void test01() {
        //查询用户名包含a，年龄在20到30之间，并且邮箱不为null的用户信息
        /**
         * SELECT uid AS id,user_name AS name,age,email,is_deleted
         * FROM t_user WHERE is_deleted=0
         * AND (user_name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
         */
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name", "a")
                .between("age", 20, 30)
                .isNotNull("email");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(user -> System.out.println(user));
    }
    @Test
    public void test02() {
        //按年龄降序查询用户，如果年龄相同则按id升序排列
        /**
         * SELECT uid AS id,user_name AS name,age,email,is_deleted
         * FROM t_user WHERE is_deleted=0 ORDER BY age DESC,id ASC
         */
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age").orderByAsc("id");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(user -> System.out.println(user));
    }
    @Test
    public void test03() {
        //删除email为空的用户
        // UPDATE t_user SET is_deleted=1 WHERE is_deleted=0 AND (email IS NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        //条件构造器也可以构建删除语句的条件
        int result = userMapper.delete(queryWrapper);
        System.out.println("受影响的行数：" + result);
    }

    @Test
    public void test04() {
        //将（年龄大于20并且用户名中包含有a）或邮箱为null的用户信息修改
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //UPDATE t_user SET user_name=? WHERE is_deleted=0 AND (user_name LIKE ? AND age > ? OR email IS NULL)
        queryWrapper.like("user_name", "a")
                .gt("age", 20)
                .or()
                .isNull("email");
        User user = new User();
        user.setName("小明");
        int ret = userMapper.update(user, queryWrapper);
        System.out.println("受影响的行数：" + ret);
    }
    @Test
    public void test05() {
        //将用户名中包含有a并且（年龄大于20或邮箱为null）的用户信息修改
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //UPDATE t_user SET user_name=? WHERE is_deleted=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
        queryWrapper.like("user_name", "a")
                //lambda表达式内的逻辑优先运算
                .and(qw -> qw.gt("age", 20).or().isNull("email"));
        User user = new User();
        user.setName("小明");
        int ret = userMapper.update(user, queryWrapper);
        System.out.println("受影响的行数：" + ret);
    }
    @Test
    public void test06() {
        //查询用户信息的username和age字段
        //SELECT user_name,age FROM t_user WHERE is_deleted=0
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_name", "age");
        //selectMaps()返回Map集合列表，通常配合select()使用，避免User对象中没有被查询到的列值为null
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(map -> System.out.println(map));
    }

    @Test
    public void test07() {
        //查询id大于等于3的用户信息
        /**
         * Preparing: SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND
         * (uid IN (select uid from t_user where uid >= 3))
         */
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("uid", "select uid from t_user where uid >= 3");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(user -> System.out.println(user));

    }

    @Test
    public void test08() {
        //将（年龄大于20或邮箱为null）并且用户名中包含有a的用户信息修改
        //组装set子句以及修改条件
        //UPDATE t_user SET user_name=? WHERE is_deleted=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("user_name", "a")
                .and(i -> i.gt("age", 20).or().isNull("email"))
                .set("user_name", "b");
        int ret = userMapper.update(null, updateWrapper);
        System.out.println("受影响的行数：" + ret);
    }
    @Test
    public void test09() {
        //定义查询条件，有可能为null（用户未输入或未选择）
        String username = "";
        Integer ageBegin = 10;
        Integer ageEnd = 24;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //StringUtils.isNotBlank()判断某字符串是否不为空且长度不为0且不由空白符(whitespace)构成
        if(StringUtils.isNotBlank(username)){
            queryWrapper.like("username","b");
        }
        if(ageBegin != null){
            queryWrapper.ge("age", ageBegin);
        }
        if(ageEnd != null){
            queryWrapper.le("age", ageEnd);
        }
        /**
         * SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user
         * WHERE is_deleted=0 AND (age >= ? AND age <= ?)
         */
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }
    @Test
    public void test10() {
        //定义查询条件，有可能为null（用户未输入或未选择）
        String username = "";
        Integer ageBegin = 10;
        Integer ageEnd = 24;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isBlank(username), "user_name", "b")
                .ge(ageBegin != null, "age", ageBegin)
                .le(ageEnd != null, "age", ageEnd);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test11() {
        //定义查询条件，有可能为null（用户未输入）
        String username = "a";
        Integer ageBegin = 10;
        Integer ageEnd = 24;
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        //避免使用字符串表示字段，防止运行时错误
        queryWrapper
                .like(StringUtils.isNotBlank(username), User::getName, username)
                .ge(ageBegin != null, User::getAge, ageBegin)
                .le(ageEnd != null, User::getAge, ageEnd);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }
    @Test
    public void test12() {
        //组装set子句
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(User::getAge, 18)
                .set(User::getEmail, "user@atguigu.com")
                .like(User::getName, "a")
                .and(i -> i.lt(User::getAge, 24).or().isNull(User::getEmail));
        //lambda表达式内的逻辑优先运算
        User user = new User();
        int result = userMapper.update(user, updateWrapper);
        System.out.println("受影响的行数：" + result);
    }
}
