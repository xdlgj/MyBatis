package com.xdl.mybatis_plus_demo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdl.mybatis_plus_demo.bean.Product;
import com.xdl.mybatis_plus_demo.mapper.ProductMapper;
import com.xdl.mybatis_plus_demo.service.ProductService;
import com.xdl.mybatis_plus_demo.service.UserService;
import org.springframework.stereotype.Service;

@DS("master")
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
