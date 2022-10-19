package com.qing.www.dto.common;

import com.qing.www.util.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接口返回值包装类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult {
    private String code;
    private String msg;
    private Object data;

    public static CommonResult Success(){
        return new CommonResult(CommonEnum.SUCCESS.getResultCode(),CommonEnum.SUCCESS.getResultMsg(),null);
    }
    public static CommonResult Success(Object data){
        return new CommonResult(CommonEnum.SUCCESS.getResultCode(),CommonEnum.SUCCESS.getResultMsg(),data);
    }
    public static CommonResult Error(){
        return new CommonResult(CommonEnum.ERROR.getResultCode(),CommonEnum.ERROR.getResultMsg(),null);
    }
    public static CommonResult Error(CommonEnum commonEnum){
        return new CommonResult(commonEnum.getResultCode(),commonEnum.getResultMsg(),null);
    }
    public static CommonResult Error(ServiceException serviceException){
        return new CommonResult(serviceException.getCode(),serviceException.getMessage(),null);
    }
}

