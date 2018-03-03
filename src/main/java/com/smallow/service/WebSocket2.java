package com.smallow.service;

import com.smallow.constant.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by wanghuidong on 2017/8/25.
 */
@Component
@ServerEndpoint(value = "/webSocket2/{moduleName}/{qrCode}")
@Slf4j
public class WebSocket2 {


    private StringRedisTemplate redisTemplate;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebSocket2.applicationContext = applicationContext;
    }

    private static ApplicationContext applicationContext;
    private Session session;
    private String sessionKey;
    private static CopyOnWriteArraySet<WebSocket2> webSocketSet = new CopyOnWriteArraySet<>();


    @OnOpen
    public void onOpen(Session session, @PathParam("moduleName") String moduleName,
                       @PathParam("qrCode") String qrCode) {
        this.session = session;
        this.sessionKey = qrCode;
        webSocketSet.add(this);
        log.info("【websocket2消息】有新的连接:{}, 总数:{}", qrCode, webSocketSet.size());
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        log.info("【websocket2消息】{} 连接断开, 总数:{}", this.sessionKey, webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message) {
        redisTemplate = applicationContext.getBean(StringRedisTemplate.class);
        log.info("【websocket2消息】收到客户端发来的消息:{}", message);
        if (!StringUtils.isEmpty(message)) {
            String openid = redisTemplate.opsForValue().get(String.format(RedisConstant.QRCODE_PREFIX, message));
            if (openid != null) {
                sendMessage("success_" + openid);
            }

        }
    }

    public void sendMessage(String message) {
//        for (WebSocket2 webSocket : webSocketSet) {
//            log.info("【websocket2消息】广播消息, message={}", message);
//            try {
//                webSocket.session.getBasicRemote().sendText(message);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        try {
            log.info("[给]{}回复消息:{}", this.sessionKey, message);
            this.session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
