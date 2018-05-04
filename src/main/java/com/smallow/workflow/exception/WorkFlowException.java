package com.smallow.workflow.exception;

import com.smallow.workflow.enums.ResultEnum;

/**
 * Created by wanghuidong on 2018/5/3.
 */
public class WorkFlowException extends RuntimeException {
    private Integer code;

    public WorkFlowException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public WorkFlowException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
