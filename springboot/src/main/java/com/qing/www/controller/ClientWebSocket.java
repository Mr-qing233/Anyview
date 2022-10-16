package com.qing.www.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONParser;
import com.qing.www.util.common.CommonEnum;
import com.qing.www.util.common.CommonResult;
import com.qing.www.util.exception.ServiceException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/client/{cardId}")
@Component
public class ClientWebSocket {
    private static Logger logger = LogManager.getLogger(ClientWebSocket.class.getName());
    private static final String START = "start";
    /**
     * 静态变量 记录当前在线连接数
     */
    private static int onlineCount = 0;
    /**
     * 存放各客户端对应的WebSocket对象
     */
    private static CopyOnWriteArraySet<ClientWebSocket> webSocketClientMap = new CopyOnWriteArraySet<>();
    /**
     * 与客户端的连接会话
     */
    private Session session;

    /**
     * 连接建立成功
     * @param cardId
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("cardId") String cardId,Session session){
        this.session = session;
        webSocketClientMap.add(this);
        addOnlineCount();
        logger.info("session连接id:"+session.getId()+"加入连接,当前在线:"+webSocketClientMap.size());
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose(){
        webSocketClientMap.remove(this);
        subOnlineCount();
        logger.info("1个连接断开,当前在线:"+webSocketClientMap.size());
    }

    /**
     * //[样例]收到消息+倒计时触发
     * 处理消息
     * @param msg
     * @param session
     */
    @OnMessage
    public void onMessage(String msg,Session session){
        logger.info("[客户端消息]:"+msg);
        if(START.equals(msg)){
            int x=100;
            while (x>0){
                sendMessage(String.format("当前时间值为:"+x+"s"),session);
                try {
                    Thread.sleep(100);
                }catch (Exception e){
                    logger.error("倒计时错误");
                }
                --x;
            }
        }else {
            sendMessage(msg,session);
        }
    }

    /**
     * 发生错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("wsClientMap发生错误!");
        error.printStackTrace();
    }

    /**
     * 给所有客户端发送消息
     * @param message
     */
    public void sendMsgToAll(String message){
        for(ClientWebSocket item:webSocketClientMap){
            try {
                item.session.getBasicRemote().sendText(message);
            }catch (Exception e){
                throw new ServiceException(CommonEnum.ERROR_SEND_MESSAGE_TO_ALL);
            }
        }
        logger.info("成功群送一条消息:" + webSocketClientMap.size());
    }

    /**
     * 单发消息
     * @param message
     */
    public void sendMessage(String message,Session toSession){
        try {
            toSession.getBasicRemote().sendText(message);
        }catch (Exception e){
            throw new ServiceException(CommonEnum.ERROR_SEND_MESSAGE);
        }
        logger.info("成功发送一条消息:" + message);
    }



    public static synchronized int getOnlineCount(){
        return ClientWebSocket.onlineCount;
    }
    public static synchronized void addOnlineCount(){
        ClientWebSocket.onlineCount++;
    }
    public static synchronized void subOnlineCount(){
        ClientWebSocket.onlineCount--;
    }
}
