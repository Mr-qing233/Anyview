package com.qing.www.util.exception;

import com.qing.www.util.common.CommonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ServiceExceptionHandle {
    /**
     * 如果抛出的是ServiceException则抛出该方法
     * @param serviceException 业务异常
     * @return CommonResult
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public CommonResult handle(ServiceException serviceException){
        return CommonResult.Error(serviceException);
    }
}
