package com.smallow.VO;

import lombok.Data;

/**
 * Created by wanghuidong on 2017/9/8.
 */
@Data
public class ResultVO<T>{

    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体内容. */
    private T data;
}
