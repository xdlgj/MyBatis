package com.xdl.mybatis_plus_demo;

import com.xdl.mybatis_plus_demo.bean.User;
import com.xdl.mybatis_plus_demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testGetCount() {
        //SELECT COUNT( * ) FROM user
        long count = userService.count();
        System.out.println("总记录数：" + count);
    }

    @Test
    public void testSaveBatch(){
        // SQL长度有限制，海量数据插入单条SQL无法实行，
        // 因此MP将批量插入放在了通用Service中实现，而不是通用Mapper
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setName("ybc" + i);
            user.setAge(20 + i);
            users.add(user);
        }
        //INSERT INTO user ( id, name, age ) VALUES ( ?, ?, ? )
        userService.saveBatch(users);
    }
}
