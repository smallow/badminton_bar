package com.smallow.aspect;

import com.smallow.constant.CookieConstant;
import com.smallow.constant.RedisConstant;
import com.smallow.exception.GroupAdminAuthorizeException;
import com.smallow.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by wanghuidong on 2017/8/23.
 */
@Aspect
@Component
@Slf4j
public class BadmintonAdminUserAuthorizeAspect {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.smallow.controller.*.*(..))" +
            "&& !execution(public * com.smallow.controller.BadmintonAdminUserController.*(..))")
    public void verify() {
    }


    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
//        if (request.getSession().getAttribute("openid") == null) {
//            throw new GroupAdminAuthorizeException();
//        }


        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("【登录校验】Cookie中查不到token");
            throw new GroupAdminAuthorizeException();
        }

        //去redis里查询
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
            log.warn("【登录校验】Redis中查不到token");
            throw new GroupAdminAuthorizeException();
        }

    }
}
