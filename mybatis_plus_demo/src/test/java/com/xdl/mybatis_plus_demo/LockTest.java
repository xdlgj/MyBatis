package com.xdl.mybatis_plus_demo;

import com.xdl.mybatis_plus_demo.bean.Product;
import com.xdl.mybatis_plus_demo.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LockTest {
    @Autowired
    ProductMapper productMapper;
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
}
