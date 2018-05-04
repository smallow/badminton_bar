package com.smallow.rmo.enums;

import com.smallow.enums.CodeEnum;
import lombok.Getter;

/**
 * Created by wanghuidong on 2018/4/24.
 */
@Getter
public enum EmployeeSexEnum implements CodeEnum {

    MAN(0, "男"), WOMAN(1, "女");


    private Integer code;
    private String msg;

    EmployeeSexEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
