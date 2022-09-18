package com.qing.www.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Gin
 * @since 2022-09-18
 */
@Getter
@Setter
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userUuid;

    private String userName;

    private String password;

    private String realName;

    private Integer age;

    private Integer sex;

    private LocalDateTime birthDay;

    private Integer userLevel;

    private String phone;

    private Integer role;

    private Integer status;

    private String imagePath;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    private LocalDateTime lastActiveTime;

    private Boolean deleted;

    private String wxOpenId;


}
