package com.qing.www.dto;


import lombok.Data;


/**
 * <p>
 *     WebSocket消息实体
 * </p>
 * @param <T>
 */
@Data
public class WebSocketResult<T>{
    private String code;
    private String msg;
    /**
     * flag用于表示result用途，方便数据处理
     * 0:心跳验证以及后台消息,1:公告
     */
    private Integer flag;

    private T data;

    public static <T> WebSocketResult success(T data){
        WebSocketResult<Object> webSocketResult = new WebSocketResult<>();
        webSocketResult.setCode("200");
        webSocketResult.setMsg("发送消息实体成功");
        webSocketResult.setData(data);

        return webSocketResult;
    }
    public static <T> WebSocketResult success(T data,String msg){
        WebSocketResult<Object> webSocketResult = new WebSocketResult<>();
        webSocketResult.setCode("200");
        webSocketResult.setMsg(msg);
        webSocketResult.setData(data);

        return webSocketResult;
    }

    public static <T> WebSocketResult success(T data,Integer flag){
        WebSocketResult<Object> webSocketResult = new WebSocketResult<>();
        webSocketResult.setCode("200");
        webSocketResult.setFlag(flag);
        webSocketResult.setData(data);

        return webSocketResult;
    }

    public static <T> WebSocketResult success(T data,String msg,Integer flag){
        WebSocketResult<Object> webSocketResult = new WebSocketResult<>();
        webSocketResult.setCode("200");
        webSocketResult.setMsg(msg);
        webSocketResult.setFlag(flag);
        webSocketResult.setData(data);

        return webSocketResult;
    }
}
