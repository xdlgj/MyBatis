package com.xdl.mybatis_plus_demo;

import com.xdl.mybatis_plus_demo.bean.Product;
import com.xdl.mybatis_plus_demo.bean.User;
import com.xdl.mybatis_plus_demo.enums.SexEnum;
import com.xdl.mybatis_plus_demo.mapper.ProductMapper;
import com.xdl.mybatis_plus_demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LockTest {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    UserMapper userMapper;
    @Test
    public void test01() {
        // 小李
        Product productLi = productMapper.selectById(1);
        System.out.println("小李取出的价格：" + productLi.getPrice());
        // 小王
        Product productWang = productMapper.selectById(1);
        System.out.println("小李取出的价格：" + productWang.getPrice());
        // 小李将价格加了50存入数据库
        productLi.setPrice(productLi.getPrice() + 50);
        int result1 = productMapper.updateById(productLi);
        System.out.println("小李修改结果：" + result1);
        //小王将商品减了30元，存入了数据库
        productWang.setPrice(productWang.getPrice() - 30);
        int result2 = productMapper.updateById(productWang);
        if (result2 == 0) {
            // 如果更新失败，再次更新
            Product productNew = productMapper.selectById(1);
            productNew.setPrice(productNew.getPrice() - 30 );
            result2 = productMapper.updateById(productNew);
        }
        System.out.println("小王修改结果：" + result2);
        //最后的结果
        Product productBoss = productMapper.selectById(1L);
        System.out.println("最后的结果：" + productBoss.getPrice());
    }
    @Test
    public void testSexEnum(){
        User user = new User();
        user.setName("Enum");
        user.setAge(20);
        //设置性别信息为枚举项，会将@EnumValue注解所标识的属性值存储到数据库
        user.setSex(SexEnum.MALE);
        //INSERT INTO t_user ( username, age, sex ) VALUES ( ?, ?, ? )
        //Parameters: Enum(String), 20(Integer), 1(Integer)
        //userMapper.insert(user);
        User user1 = userMapper.selectById(9);
        System.out.println(user1);
        // User(id=9, name=Enum, age=20, email=null, isDeleted=0, sex=MALE)
    }
}
