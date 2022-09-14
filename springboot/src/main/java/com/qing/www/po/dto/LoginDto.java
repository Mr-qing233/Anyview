package com.qing.www.po.dto;


import lombok.Data;


/**
 * <p>
 *     存放前端登录传入数据
 * </p>
 */
@Data
public class LoginDto {
    private Integer cardId;
    private String pwd;
}
