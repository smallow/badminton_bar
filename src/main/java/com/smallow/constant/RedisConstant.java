package com.smallow.constant;

/**
 * Created by wanghuidong on 2017/8/23.
 */
public interface RedisConstant {

    String QRCODE_PREFIX = "qrcode_%s";
    Integer QRCODE_EXPIRE=300; //临时二维码有效期5分钟

    Integer TOKEN_EXPIRE = 7200; //2小时
    String TOKEN_PREFIX = "token_%s";

}
