package com.smallow.exception;

import com.smallow.enums.ResultEnum;

/**
 * Created by wanghuidong on 2017/8/23.
 */
public class BadmintonException extends RuntimeException{
    private Integer code;

    public BadmintonException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public BadmintonException(Integer code,String message){
        super(message);
        this.code=code;

    }
}
