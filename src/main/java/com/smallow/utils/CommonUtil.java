package com.smallow.utils;


import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wanghuidong on 2018/4/18.
 */
public class CommonUtil {

    /**
     * 获取上下文
     * @return
     */
    public static String getContextPath() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String path = request.getContextPath();
        return path;
    }
}
