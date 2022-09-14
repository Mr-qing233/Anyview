package com.qing.www.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qing.www.po.Admin;
import com.qing.www.po.Student;
import com.qing.www.po.Teacher;
import com.qing.www.po.dto.LoginDto;

public interface ILoginService extends IService<LoginDto> {
    public Admin adminLogin(Integer cardId,String pwd);
    public Teacher teacherLogin(Integer cardId, String pwd);
    public Student studentLogin(Integer cardId, String pwd);
}
