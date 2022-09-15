package com.qing.www.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

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
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员id
     */
    @TableId(value = "adminId", type = IdType.AUTO)
    private Integer adminId;

    /**
     * 管理员名字
     */
    @TableField(value = "adminName")
    private String adminName;

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
     * 管理员权限
     */
    private Integer permission;

    /**
     * 设置token
     */
    @TableField(exist = false)
    private String token;


}
