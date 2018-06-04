package com.smallow.workflow.enums;

import lombok.Getter;

/**
 * Created by wanghuidong on 2018/5/8.
 */
@Getter
public enum WorkFlowDealTypeEnum implements IntegerCodeEnum {
    PENDING(0, "未处理"),
    PASS(1, "通过"),
    REJECT(2, "拒绝"),
    FINISH(3, "终止");


    private Integer code;
    private String name;

    WorkFlowDealTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
