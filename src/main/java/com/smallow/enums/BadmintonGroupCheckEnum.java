package com.smallow.enums;

import lombok.Getter;

/**
 * Created by wanghuidong on 2017/8/21.
 */
@Getter
public enum BadmintonGroupCheckEnum implements CodeEnum {


    NEED(0, "需要审核"), NONEED(1, "不需要审核");


    private Integer code;
    private String msg;

    BadmintonGroupCheckEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
