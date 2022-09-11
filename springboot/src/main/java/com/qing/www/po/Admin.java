package com.qing.www.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    private String adminName;

    /**
     * 账号
     */
    private Integer cardId;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 管理员权限
     */
    private Integer permission;


}
