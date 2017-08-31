package com.smallow.enums;

import lombok.Getter;

/**
 * Created by wanghuidong on 2017/8/23.
 */
@Getter
public enum  QrSceneEnum {

    LOGIN(1, "登录场景");





    private Integer code;
    private String msg;

    QrSceneEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
