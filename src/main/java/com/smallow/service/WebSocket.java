package com.smallow.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.smallow.constant.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.server.standard.SpringConfigurator;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by wanghuidong on 2017/8/25.
 */
@Component
@ServerEndpoint(value = "/webSocket")
@Slf4j
public class WebSocket {



    private StringRedisTemplate redisTemplate;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebSocket.applicationContext = applicationContext;
    }

    private static ApplicationContext applicationContext;


    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();


    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        log.info("【websocket消息】有新的连接, 总数:{}", webSocketSet.size());
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        log.info("【websocket消息】连接断开, 总数:{}", webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message) {
        redisTemplate=applicationContext.getBean(StringRedisTemplate.class);
        log.info("【websocket消息】收到客户端发来的消息:{}", message);
        if(!StringUtils.isEmpty(message)){
            String openid=redisTemplate.opsForValue().get(String.format(RedisConstant.QRCODE_PREFIX, message));
            if(openid!=null){
                sendMessage("success_"+message);
            }

        }

    }

    public void sendMessage(String message) {
        for (WebSocket webSocket : webSocketSet) {
            log.info("【websocket消息】广播消息, message={}", message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
