package com.qing.www.controller;


import com.qing.www.po.dto.LoginDto;
import com.qing.www.util.common.CommonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 *     前端控制器
 * </p>
 */
@RestController
@RequestMapping("/user")
public class LoginController {
    @PostMapping("/login")
    public CommonResult login(@RequestBody LoginDto loginDto){


        return CommonResult.Success();
    }
}
