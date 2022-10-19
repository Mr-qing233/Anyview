package com.qing.www.component;

import com.qing.www.dto.common.CommonEnum;
import com.qing.www.util.WebSocketEncoder;
import com.qing.www.util.exception.ServiceException;
import com.qing.www.vo.WebSocketClient;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/client/{cardId}",encoders = {WebSocketEncoder.class})
@Component
public class ClientWebSocket {
    private static Logger logger = LogManager.getLogger(ClientWebSocket.class.getName());
    private static final String START = "start";



    /**
     * 静态变量 记录当前在线连接数
     */
    private static int onlineCount = 0;

    /**
     * session集合,存放对应的session
     */
    private static ConcurrentHashMap<String, WebSocketClient> webSocketMap = new ConcurrentHashMap<>();


    /**
     * concurrent包的线程安全Set,用来存放每个客户端对应的WebSocket对象。
     */

    private static CopyOnWriteArraySet<WebSocketClient> webSocketSet = new CopyOnWriteArraySet<>();
    /**
     * 与客户端的连接会话
     */
    private Session session;
    private String cardId="";
    /**
     * 连接建立成功
     * @param cardId
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("cardId") String cardId,Session session){
        logger.info("WebSocket建立连接中,连接用户ID：{}", cardId);
        try {
            WebSocketClient historySession = webSocketMap.get(cardId);
            // historySession不为空,说明已经有人登陆账号,应该删除登陆的WebSocket对象
            if (historySession != null) {
                webSocketSet.remove(historySession);
                historySession.getSession().close();
            }
        } catch (Exception e) {
            throw new ServiceException(CommonEnum.WEBSOCKET_CLIENT_ERROR);
        }
        // 建立连接
        this.session = session;
        this.cardId = cardId;

        WebSocketClient webSocketClient = new WebSocketClient();
        webSocketClient.setSession(session);
        webSocketClient.setUri(session.getRequestURI().toString());

        webSocketSet.add(webSocketClient);
        webSocketMap.put(cardId, webSocketClient);
        addOnlineCount();
        logger.info("session连接id:"+session.getId()+"加入连接,当前在线:"+webSocketSet.size());

    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        subOnlineCount();
        logger.info("1个连接断开,当前在线:"+webSocketSet.size());
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
                    Thread.sleep(1000);
                }catch (Exception e){
                    logger.error("倒计时错误");
                }
                --x;
            }
        }else {
            sendMessage(cardId,msg);
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
        logger.info("发送消息：{}", message);
        for (WebSocketClient webSocket : webSocketSet) {
            try {
                webSocket.getSession().getBasicRemote().sendText(message);
            } catch (IOException e) {
                logger.error("群发消息发生错误：" + e.getMessage(), e);
                throw new ServiceException(CommonEnum.ERROR_SEND_MESSAGE_TO_ALL);
            }
        }
    }

    /**
     * 单发消息[字符串]
     * @param message
     */
    public static void sendMessage(String cardId,String message){
        try {
            WebSocketClient webSocketClient = webSocketMap.get(cardId);
            if(webSocketClient!=null){
                webSocketClient.getSession().getBasicRemote().sendText(message);
                logger.info("成功发送一条消息:" + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException(CommonEnum.ERROR_SEND_MESSAGE);
        }

    }

    public static void sendMessage(String cardId,Object object){
        try {
            WebSocketClient webSocketClient = webSocketMap.get(cardId);
            if(webSocketClient!=null){
                webSocketClient.getSession().getBasicRemote().sendObject(object);
                logger.info("成功发送一个对象:" + object);
            }
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
            throw new ServiceException(CommonEnum.ERROR_SEND_MESSAGE);
        }
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
