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
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 老师id
     */
    @TableId(value = "teacherId", type = IdType.AUTO)
    private Integer teacherId;

    /**
     * 老师名字
     */
    @TableField(value = "teacherName")
    private String teacherName;

    /**
     * 学院
     */
    private String institute;

    /**
     * 课程
     */
    private String course;

    /**
     * 账号
     */
    @TableField(value = "cardId")
    private Integer cardId;

    /**
     * 密码
     */
    @JsonIgnore
    private String pwd;

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
