package com.qing.www.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import com.qing.www.util.TokenUtils;
import com.qing.www.util.common.CommonEnum;
import com.qing.www.util.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, LoginDto> implements ILoginService {

    private static final Log LOG=Log.get();

    @Resource
    private IAdminService adminService;
    @Resource
    private ITeacherService teacherService;
    @Resource
    private IStudentService studentService;

    @Override
    public Admin adminLogin(LoginDto loginDto) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cardId", loginDto.getCardId());
        queryWrapper.eq("pwd", loginDto.getPwd());
        Admin one;
        try{
            one = adminService.getOne(queryWrapper);//获取数据库对应数据
        }catch (Exception e){
            LOG.error(e);
            throw new ServiceException(CommonEnum.ERROR_SEARCH_SERVICE);//查找失败抛出业务异常
        }
        if(one != null){//如果查询有结果
            //设置token
            String token = TokenUtils.generateToken(one.getAdminId().toString(), one.getPwd());
            one.setToken(token);
            return one;//返回查询信息，登录成功

            //考虑第二种方案，只返回token存储到浏览器，后续需要其他信息再通过token获取
        }else {//如果查询为空，则账号不存在
            throw new ServiceException(CommonEnum.USER_PASSWORD_ERROR);
        }
    }

    @Override
    public Teacher teacherLogin(LoginDto loginDto) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cardId", loginDto.getCardId());
        queryWrapper.eq("pwd", loginDto.getPwd());
        Teacher one;
        try{
            one = teacherService.getOne(queryWrapper);//获取数据库对应数据
        }catch (Exception e){
            LOG.error(e);
            throw new ServiceException(CommonEnum.ERROR_SEARCH_SERVICE);//查找失败抛出业务异常
        }
        if(one != null){//如果查询有结果
            //设置token
            String token = TokenUtils.generateToken(one.getTeacherId().toString(), one.getPwd());
            one.setToken(token);
            return one;//返回查询信息，登录成功

            //考虑第二种方案，只返回token存储到浏览器，后续需要其他信息再通过token获取
        }else {//如果查询为空，则账号不存在
            throw new ServiceException(CommonEnum.USER_PASSWORD_ERROR);
        }
    }

    @Override
    public Student studentLogin(LoginDto loginDto) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cardId", loginDto.getCardId());
        queryWrapper.eq("pwd", loginDto.getPwd());
        Student one;
        try{
            one = studentService.getOne(queryWrapper);//获取数据库对应数据
        }catch (Exception e){
            LOG.error(e);
            throw new ServiceException(CommonEnum.ERROR_SEARCH_SERVICE);//查找失败抛出业务异常
        }
        if(one != null){//如果查询有结果
            //设置token
            String token = TokenUtils.generateToken(one.getStudentId().toString(), one.getPwd());
            one.setToken(token);
            return one;//返回查询信息，登录成功

            //考虑第二种方案，只返回token存储到浏览器，后续需要其他信息再通过token获取
        }else {//如果查询为空，则账号不存在
            throw new ServiceException(CommonEnum.USER_PASSWORD_ERROR);
        }
    }
}
