package com.smallow.aspect;

import com.smallow.exception.GroupAdminAuthorizeException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wanghuidong on 2017/8/23.
 */
@Aspect
@Component
@Slf4j
public class BadmintonAdminUserAuthorizeAspect {


    @Pointcut("execution(public * com.smallow.controller.*.*(..))" +
            "&& !execution(public * com.smallow.controller.BadmintonAdminUserController.*(..))")
    public void verify() {
    }


    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        if (request.getSession().getAttribute("openid") == null) {
            throw new GroupAdminAuthorizeException();
        }

    }
}
