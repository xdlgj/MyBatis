package com.xdl.mybatis_plus_demo;

import com.xdl.mybatis_plus_demo.service.ProductService;
import com.xdl.mybatis_plus_demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DynamicDataSourceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Test
    public void test01() {
        System.out.println(userService.getById(1L));
        System.out.println(productService.getById(1L));
    }
}
