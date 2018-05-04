package com.smallow.service;


import java.util.List;
import java.util.Map;

/**
 * Created by wanghuidong on 2018/3/2.
 */
public interface ConstantService {

    /**
     * 查找行政区划代码表
     * @return
     */
    List<Map<String, Object>> findAreaConstants();
}
