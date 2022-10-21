package com.qing.www.util;

import com.alibaba.fastjson.JSONObject;
import com.qing.www.dto.WebSocketResult;
import com.qing.www.dto.common.CommonEnum;
import com.qing.www.util.exception.ServiceException;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.HashMap;

public class WebSocketResultEncoder implements Encoder.Text<WebSocketResult> {
    @Override
    public String encode(WebSocketResult webSocketResult) throws ServiceException {
        /*
         * 返回Object序列化后的json字符串
         */
        try {
            return JSONObject.toJSONString(webSocketResult);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException(CommonEnum.ENCODER_ERROR);
        }
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
