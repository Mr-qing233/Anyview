package com.qing.www.util.exception;

import com.qing.www.util.common.CommonEnum;
import lombok.Getter;

/**
 * 自定义异常
 */
@Getter
public class ServiceException extends RuntimeException{
    private String code;
    private String msg;

    public ServiceException(CommonEnum commonEnum){
        super(commonEnum.getResultMsg());
        this.code=commonEnum.getResultCode();
        this.msg=commonEnum.getResultMsg();
    }
}
