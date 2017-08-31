package com.smallow.utils;

import com.smallow.enums.CodeEnum;

/**
 * Created by wanghuidong on 2017/8/21.
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }

        return null;

    }
}
