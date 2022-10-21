package com.qing.www.vo;


import lombok.Data;

@Data
public class Dialog {
    /**
     * 是否开启前端窗口
     */
    private boolean flag;
    /**
     * dialog窗口信息
     */
    private String msg;
}
