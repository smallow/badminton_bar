package com.smallow.controller;

import com.smallow.entity.Group;
import com.smallow.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wanghuidong on 2017/8/22.
 */
@Controller
@RequestMapping("/admin/group")
@Slf4j
public class BadmintonGroupController {


    @Autowired
    private GroupService badmintonGroupService;

    @GetMapping("/list")
    public ModelAndView list(Integer page, Integer groupStatus, String groupManagerPhone, String groupName) {
        List<Group> list = new ArrayList<>();

        return new ModelAndView("group/list");
    }

}
