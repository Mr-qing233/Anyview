package com.qing.www.service.impl;

import com.qing.www.entity.User;
import com.qing.www.mapper.UserMapper;
import com.qing.www.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Gin
 * @since 2022-09-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
