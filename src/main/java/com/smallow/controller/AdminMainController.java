package com.smallow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by wanghuidong on 2017/9/7.
 */
@Controller
@RequestMapping("/admin/main")
public class AdminMainController {


    @GetMapping("/adminMain")
    public ModelAndView adminMain(Map<String, Object> map, HttpServletRequest request) {

        return new ModelAndView("admin/main");
    }
}
