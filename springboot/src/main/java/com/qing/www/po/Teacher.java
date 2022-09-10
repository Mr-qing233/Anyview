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
 * @since 2022-09-10
 */
@Getter
@Setter
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 老师id
     */
    private Integer teacherId;

    /**
     * 老师名字
     */
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
    private Integer cardId;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 权限
     */
    private Integer permission;
}
