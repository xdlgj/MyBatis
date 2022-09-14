package com.xdl.mybatisplus.service.impl;

import com.xdl.mybatisplus.entity.User;
import com.xdl.mybatisplus.mapper.UserMapper;
import com.xdl.mybatisplus.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xdl
 * @since 2022-09-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
