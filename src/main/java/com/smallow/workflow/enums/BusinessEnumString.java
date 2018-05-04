package com.smallow.workflow.enums;

import lombok.Getter;

/**
 * Created by wanghuidong on 2018/5/3.
 */
@Getter
public enum BusinessEnumString implements StringCodeEnum {
    ONDAY("1001", "一日请假");


    private String code;
    private String name;

    BusinessEnumString(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
