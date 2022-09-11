package com.qing.www.util.common;

/**
 * 接口用于枚举返回码
 */

public interface BaseErrorInfoInterface {
    /**
     * 错误码
     */
    String getResultCode();

    /**
     * 错误信息
     */
    String getResultMsg();
}
