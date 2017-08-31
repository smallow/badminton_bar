package com.smallow.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by wanghuidong on 2017/8/22.
 */
@Controller
@RequestMapping("/admin/group")
@Slf4j
public class BadmintonGroupController {


    @GetMapping("/list")
    public ModelAndView list(HttpServletRequest request) {
        String openid = (String) request.getSession().getAttribute("openid");
        Integer groupId = Integer.parseInt((String) request.getSession().getAttribute("groupId"));
        log.info("请求group list openid={},groupId={}", openid,groupId);
        return new ModelAndView("group/list");
    }

}
