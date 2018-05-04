package com.smallow.workflow.enums;

import lombok.Getter;

/**
 * Created by wanghuidong on 2018/5/4.
 */
@Getter
public enum PendingItemStatusEnum implements IntegerCodeEnum {
    PASSED(0, "通过"),

    REJECT(1, "拒绝");

    private Integer code;
    private String name;

    PendingItemStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
