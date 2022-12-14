package com.qing.www.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @TableId(value = "studentId", type = IdType.AUTO)
    private Integer studentId;

    /**
     * 学生姓名
     */
    @TableField(value = "studentName")
    private String studentName;

    /**
     * 学生年级
     */
    private Integer grade;


    /**
     * 班级唯一Id
     */
    @TableField(value = "clazzId")
    private Integer clazzId;

    /**
     * 账号
     */
    @TableField(value = "cardId")
    private String cardId;

    /**
     * 密码
     */
    @JsonIgnore
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
    @TableField(exist = false)
    private String token;


}
