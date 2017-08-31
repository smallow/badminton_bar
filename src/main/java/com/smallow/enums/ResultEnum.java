package com.smallow.enums;

import lombok.Getter;

/**
 * Created by wanghuidong on 2017/8/23.
 */
@Getter
public enum ResultEnum {
    WECHAT_MP_ERROR(10, "微信公众账号方面错误"),
    LOGIN_FAIL(20, "登录失败, 登录信息不正确"),
    QRCODE_ERROR(30, "登录验证码生成失败"),
    ADMIN_EMPTY(40, "该微信号尚未创建任何群组管理"),
    LOGIN_QRCODE_TIMEOUT(50, "登录验证码超时"),
    LOGIN_ILLEGAL(60, "非法登录"),
    SUCCESS(0, "成功");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
