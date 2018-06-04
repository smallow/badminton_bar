package com.smallow.workflow.enums;

import lombok.Getter;

/**
 * Created by wanghuidong on 2018/5/3.
 */
@Getter
public enum ResultEnum {
    SUCCESS(0, "成功"),
    APPROVAL_PROCESS_NOT_EXIST(10, "流程不存在"),
    APPROVAL_STEP_NOT_EXIST(20, "流程步骤未定义"),
    PENDING_ITEM_WORK_NOT_EXIST(30, "待办业务数据为空"),
    PENDING_ITEM_BUSINESS_CODE_NOT_EXIST(40, "待办业务类型代码为空"),
    PENDING_ITEM_NEXT_STEP_NOT_EXIST(50, "下一步流程为空"),
    LASTE_PENDING_ITEM(60, "已经是最终步骤")
    ;
    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
