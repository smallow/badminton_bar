package com.smallow.workflow.utils;


import com.smallow.workflow.enums.IntegerCodeEnum;
import com.smallow.workflow.enums.StringCodeEnum;

/**
 * Created by wanghuidong on 2018/5/4.
 */
public class EnumUtil {

    public static <T extends StringCodeEnum> T getByCode(String code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }

        return null;

    }

    public static <T extends IntegerCodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.intValue() == each.getCode().intValue()) {
                return each;
            }
        }

        return null;

    }
}
