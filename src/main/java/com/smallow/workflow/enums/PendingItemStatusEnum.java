package com.smallow.workflow.enums;

import lombok.Getter;

/**
 * Created by wanghuidong on 2018/5/4.
 */
@Getter
public enum PendingItemStatusEnum implements IntegerCodeEnum {
    PENDING(0, "待处理"),
    FINISHED(1, "已处理");

    private Integer code;
    private String name;

    PendingItemStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
