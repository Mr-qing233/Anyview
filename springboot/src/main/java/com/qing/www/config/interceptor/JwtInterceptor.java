package com.qing.www.config.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWTException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qing.www.po.Admin;
import com.qing.www.po.Student;
import com.qing.www.po.Teacher;
import com.qing.www.service.IAdminService;
import com.qing.www.service.IStudentService;
import com.qing.www.service.ITeacherService;
import com.qing.www.dto.common.CommonEnum;
import com.qing.www.util.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private IAdminService adminService;
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private IStudentService studentService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        //不是映射到方法则通过
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        //执行认证
        if(StrUtil.isBlank(token)){
            throw new ServiceException(CommonEnum.TOKEN_NOT_FOUND);
        }
        //获取token中的userId
        String cardId;
        try {
            cardId = JWT.decode(token).getAudience().get(0);
        }catch (JWTException j){
            throw new ServiceException(CommonEnum.TOKEN_VALIDATE_ERROR);
        }

        int length = cardId.length();

        switch (length){
            case '4':
                QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
                adminQueryWrapper.eq("cardId",cardId);
                Admin admin = adminService.getOne(adminQueryWrapper);
                if (admin==null){
                    throw new ServiceException(CommonEnum.USER_NOT_FOUND);
                }
                //用户密码加签验证token
                JWTVerifier jwtVerifierAdmin = JWT.require(Algorithm.HMAC256(admin.getPwd())).build();
                try {
                    jwtVerifierAdmin.verify(token);
                }catch (JWTVerificationException e){
                    throw new ServiceException(CommonEnum.TOKEN_VALIDATE_ERROR);
                }
            case '6':
                QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
                teacherQueryWrapper.eq("cardId",cardId);
                Teacher teacher = teacherService.getOne(teacherQueryWrapper);
                if (teacher==null){
                    throw new ServiceException(CommonEnum.USER_NOT_FOUND);
                }
                //用户密码加签验证token
                JWTVerifier jwtVerifierTeacher = JWT.require(Algorithm.HMAC256(teacher.getPwd())).build();
                try {
                    jwtVerifierTeacher.verify(token);
                }catch (JWTVerificationException e) {
                    throw new ServiceException(CommonEnum.TOKEN_VALIDATE_ERROR);
                }
            case '8':
                QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
                studentQueryWrapper.eq("cardId",cardId);
                Student student = studentService.getOne(studentQueryWrapper);
                if (student==null){
                    throw new ServiceException(CommonEnum.USER_NOT_FOUND);
                }
                //用户密码加签验证token
                JWTVerifier jwtVerifierStudent = JWT.require(Algorithm.HMAC256(student.getPwd())).build();
                try {
                    jwtVerifierStudent.verify(token);
                }catch (JWTVerificationException e) {
                    throw new ServiceException(CommonEnum.TOKEN_VALIDATE_ERROR);
                }
        }
        return true;
    }
}
