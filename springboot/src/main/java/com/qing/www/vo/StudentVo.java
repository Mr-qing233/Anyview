package com.qing.www.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class StudentVo {
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
    /**
     * 学院
     */
    private String institute;

    /**
     * 专业
     */
    private String major;

    /**
     * 班别
     */
    private Integer number;
}
