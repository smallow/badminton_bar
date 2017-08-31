package com.smallow.wechat.handler;


import com.smallow.constant.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
@Slf4j
public class ScanHandler extends AbstractHandler {

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        String sceneStr = wxMpXmlMessage.getEventKey();
        String openid = wxMpXmlMessage.getFromUser();
        Integer expire = RedisConstant.QRCODE_EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.QRCODE_PREFIX, sceneStr), openid, expire, TimeUnit.SECONDS);
        logger.info("【扫描设置redis缓存成功】scenStr={},openid={}", sceneStr, openid);
        return null;
    }
}
