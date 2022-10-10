package com.qing.www.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qing.www.po.Admin;
import com.qing.www.po.Student;
import com.qing.www.po.Teacher;
import com.qing.www.po.dto.LoginDto;

public interface ILoginService extends IService<LoginDto> {
    public Admin adminLogin(LoginDto loginDto);
    public Teacher teacherLogin(LoginDto loginDto);
    public Student studentLogin(LoginDto loginDto);

}
