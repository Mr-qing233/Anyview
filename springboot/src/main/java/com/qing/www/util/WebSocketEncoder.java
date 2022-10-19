package com.qing.www.util;


import com.alibaba.fastjson.JSONObject;
import com.qing.www.dto.common.CommonEnum;
import com.qing.www.util.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.HashMap;

/**
 * <p>
 *     WebSocket编码器
 * </p>
 */
public class WebSocketEncoder implements Encoder.Text<HashMap>{
    private static final Logger log = LoggerFactory.getLogger(WebSocketEncoder.class);


    /**
     * 参数 hashMap 要和  Encoder.Text<T>保持一致
     * @param hashMap
     * @return
     * @throws ServiceException
     */
    @Override
    public String encode(HashMap hashMap) throws ServiceException{
        /*
         * 返回Object序列化后的json字符串
         */
        try {
            return JSONObject.toJSONString(hashMap);
        }catch (Exception e){
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
