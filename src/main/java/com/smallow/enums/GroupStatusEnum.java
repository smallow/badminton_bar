package com.smallow.enums;

import lombok.Getter;

/**
 * Created by wanghuidong on 2017/8/31.
 */
@Getter
public enum GroupStatusEnum implements CodeEnum{


    APPLY(0,"申请中"),PASSED(1,"已开通"),CANCEL(2,"已注销"),;

    private Integer code;
    private String msg;

    GroupStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
