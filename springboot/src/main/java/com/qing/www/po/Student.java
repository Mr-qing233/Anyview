package com.qing.www.po;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Gin
 * @since 2022-09-11
 */
@Getter
@Setter
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生id
     */
    private Integer studentId;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 学生年级
     */
    private Integer grade;

    /**
     * 学院
     */
    private String institue;

    /**
     * 专业
     */
    private String major;

    /**
     * 班级
     */
    private Integer clazz;

    /**
     * 账号
     */
    private Integer cardId;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 性别
     */
    private String sex;

    /**
     * 权限
     */
    private Integer permission;

    /**
     * 设置token
     */
    private String token;


}
