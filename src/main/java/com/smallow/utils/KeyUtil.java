package com.smallow.utils;

import org.apache.commons.codec.binary.Hex;

import java.util.Random;
import java.util.UUID;

/**
 * Created by wanghuidong on 2017/8/24.
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式: 时间+随机数
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }


    public static synchronized Integer genUniqueSceneId() {
        Random random = new Random();
        return new Integer(String.format("%d%s%d",1,String.format("%d",System.nanoTime()).substring(6,14),random.nextInt(9)));
    }



}
