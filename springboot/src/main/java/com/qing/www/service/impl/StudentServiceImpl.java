package com.qing.www.service.impl;

import com.qing.www.po.Student;
import com.qing.www.dao.StudentMapper;
import com.qing.www.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Gin
 * @since 2022-09-10
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
