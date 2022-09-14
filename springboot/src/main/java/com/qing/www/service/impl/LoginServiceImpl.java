package com.qing.www.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qing.www.dao.LoginMapper;
import com.qing.www.po.Admin;
import com.qing.www.po.Student;
import com.qing.www.po.Teacher;
import com.qing.www.po.dto.LoginDto;
import com.qing.www.service.IAdminService;
import com.qing.www.service.ILoginService;
import com.qing.www.service.IStudentService;
import com.qing.www.service.ITeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, LoginDto> implements ILoginService {

    @Resource
    private IAdminService adminService;
    @Resource
    private ITeacherService teacherService;
    @Resource
    private IStudentService studentService;

    @Override
    public Admin adminLogin(Integer cardId, String pwd) {

        return null;
    }

    @Override
    public Teacher teacherLogin(Integer cardId, String pwd) {

        return null;
    }

    @Override
    public Student studentLogin(Integer cardId, String pwd) {

        return null;
    }
}
