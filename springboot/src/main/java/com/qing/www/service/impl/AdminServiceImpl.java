package com.qing.www.service.impl;

import com.qing.www.po.Admin;
import com.qing.www.dao.AdminMapper;
import com.qing.www.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Gin
 * @since 2022-09-11
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
