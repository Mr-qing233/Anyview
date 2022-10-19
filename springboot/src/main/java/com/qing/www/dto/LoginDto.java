package com.qing.www.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


/**
 * <p>
 *     存放前端登录传入数据
 * </p>
 */
@Data
public class LoginDto {
    @JsonProperty("cardId")
    private Integer cardId;
    @JsonProperty("pwd")
    private String pwd;
}
