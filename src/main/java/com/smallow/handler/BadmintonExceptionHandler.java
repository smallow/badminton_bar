package com.smallow.handler;

import com.smallow.config.ProjectUrlConfig;
import com.smallow.exception.GroupAdminAuthorizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wanghuidong on 2017/8/23.
 */
@ControllerAdvice
public class BadmintonExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @ExceptionHandler(value = GroupAdminAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getWechatMpAuthorize())
                .concat("/badminton/admin/login"));
//                .concat("?returnUrl=")
//                .concat(projectUrlConfig.getBadminton())
//                .concat("/badminton/admin/login"));
    }
}
