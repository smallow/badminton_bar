package com.smallow.handler;

import com.smallow.config.ProjectUrlConfig;
import com.smallow.exception.GroupAdminAuthorizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wanghuidong on 2017/8/23.
 */
@ControllerAdvice
public class BadmintonExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @ExceptionHandler(value = GroupAdminAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String path=request.getContextPath();
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getWechatMpAuthorize())
                .concat(path)
                .concat("/admin/login"));
//                .concat("?returnUrl=")
//                .concat(projectUrlConfig.getBadminton())
//                .concat("/badminton/admin/login"));
    }
}
