package com.qing.www.controller;


import com.qing.www.po.Admin;
import com.qing.www.po.Student;
import com.qing.www.po.Teacher;
import com.qing.www.po.dto.LoginDto;
import com.qing.www.service.ILoginService;
import com.qing.www.service.impl.LoginServiceImpl;
import com.qing.www.util.common.CommonEnum;
import com.qing.www.util.common.CommonResult;
import com.qing.www.util.exception.ServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * <p>
 *     前端控制器
 * </p>
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Resource
    private ILoginService loginService;

    @PostMapping("/login")
    public CommonResult login(@RequestBody LoginDto loginDto){
        int length = loginDto.getCardId().toString().length();
        switch (length){
            case 4:
                Admin admin = loginService.adminLogin(loginDto);
                return CommonResult.Success(admin);
            case 6:
                Teacher teacher = loginService.teacherLogin(loginDto);
                return CommonResult.Success(teacher);
            case 8:
                Student student = loginService.studentLogin(loginDto);
                return CommonResult.Success(student);
            default:
                throw new ServiceException(CommonEnum.ACCOUNT_ERROR);
        }

    }
}
