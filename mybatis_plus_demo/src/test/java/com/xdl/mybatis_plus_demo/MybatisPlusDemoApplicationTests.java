package com.xdl.mybatis_plus_demo;

import com.xdl.mybatis_plus_demo.bean.User;
import com.xdl.mybatis_plus_demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisPlusDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        //selectList()根据MP内置的条件构造器查询一个list集合，null表示没有条件，即查询所有
        userMapper.selectList(null).forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        User user = new User(null, "张三", 23, "zhangsan@atguigu.com", null);
        //INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        int result = userMapper.insert(user);
        System.out.println("受影响行数："+result);
        //1475754982694199298
        System.out.println("id自动获取："+user.getId());
    }
    @Test
    public void testDeleteByID() {
        //通过id删除用户信息
        //DELETE FROM user WHERE id=?
        int result = userMapper.deleteById(4);
        System.out.println("受影响行数："+result);
    }
    @Test
    public void testDeleteByIds() {
        ////通过多个id批量删除
        // DELETE FROM user WHERE id IN ( ? , ? )
        List<? extends Number> ids = Arrays.asList(2L, 3);
        int result = userMapper.deleteBatchIds(ids);
        System.out.println("受影响行数："+result);
    }
    @Test
    public void testDeleteMap() {
        // 根据map集合中所设置的条件删除记录, map中元素的关系是并
        // DELETE FROM user WHERE name = ? AND age = ?
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Billie");
        map.put("age", 21);
        int result = userMapper.deleteByMap(map);
        System.out.println("受影响行数："+result);
    }
    @Test
    public void testUpdateById(){
        User user = new User(6L, "admin", 22, null, null);
        // 为null的字段不会被更新
        //UPDATE user SET name=?, age=? WHERE id=?
        int result = userMapper.updateById(user);
        System.out.println("受影响行数："+result);
    }

    @Test
    public void testSelectById(){
        //根据id查询用户信息
        //SELECT id,name,age,email FROM user WHERE id=?
        User user = userMapper.selectById(4L);
        System.out.println(user);
    }
    @Test
    public void testSelectByIds(){
        //根据id批量查询用户信息
        //SELECT id,name,age,email FROM user WHERE id IN ( ? , ? , ? )
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 4L, 5L));
        users.forEach(user -> System.out.println());
    }
    @Test
    public void testSelectByMap(){
        //通过map条件查询用户信息
        //SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
        Map<String, Object> map = new HashMap<>();
        map.put("age", 22);
        map.put("name", "admin");
        List<User> list = userMapper.selectByMap(map);
        list.forEach(System.out::println);
    }
    @Test
    public void testSelectAll(){
        //查询所有用户信息
        //SELECT id,name,age,email FROM user
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void testGetUserMapById() {
        Map<String, Object> userMap = userMapper.getUserMapById(4L);
        System.out.println(userMap);
        //{name=Sandy, id=4, age=21, email=test4@baomidou.com}
    }

}
