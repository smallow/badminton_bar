package com.smallow.controller;

import com.smallow.VO.ResultVO;
import com.smallow.entity.Group;
import com.smallow.service.GroupService;
import com.smallow.utils.ResultVOUtil;
import freemarker.template.utility.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wanghuidong on 2017/8/22.
 */
@Controller
@RequestMapping("/admin/group")
@Slf4j
public class BadmintonGroupController {

    @Autowired
    private GroupService groupService;



    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                             @RequestParam(value = "groupStatus", defaultValue = "0") Integer groupStatus,
                             @RequestParam(value = "groupManagerPhone", defaultValue = "") String groupManagerPhone,
                             @RequestParam(value = "groupName", defaultValue = "") String groupName,
                             @RequestParam(value = "groupManagerName", defaultValue = "") String groupManagerName,
                             Map<String, Object> map
                             ) {
        Map<String,Object> param=new HashMap<>();
        PageRequest pageRequest=new PageRequest(page-1,pageSize);
        if (!StringUtils.isEmpty(groupStatus)){
            param.put("groupStatus",groupStatus);
        }
        if(!StringUtils.isEmpty(groupManagerPhone)){
            param.put("groupManagerPhone",groupManagerPhone);
        }
        if(!StringUtils.isEmpty(groupName)){
            param.put("groupName",groupName);
        }
        Page<Group> groupPage=groupService.findList(param,pageRequest);
        map.put("groupPage", groupPage);
        map.put("currentPage", page);
        map.put("pageSize", pageSize);
        return new ModelAndView("admin/group/list");
    }
    @GetMapping("/list2")
    @ResponseBody
    public ResultVO<List<Group>> list(){
        Map<String,Object> param=new HashMap<>();
        PageRequest pageRequest=new PageRequest(0,10);
        Page<Group> groupPage=groupService.findList(param,pageRequest);
        return ResultVOUtil.success(groupPage.getContent());
    }
//    @PostMapping("/list")
//    @ResponseBody
//    public ResultVO<Page> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
//                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
//                                @RequestParam(value = "groupStatus", defaultValue = "0") Integer groupStatus,
//                                @RequestParam(value = "groupManagerPhone", defaultValue = "") String groupManagerPhone,
//                                @RequestParam(value = "groupName", defaultValue = "") String groupName,
//                                @RequestParam(value = "groupManagerName", defaultValue = "") String groupManagerName){
//        Map<String,Object> param=new HashMap<>();
//        PageRequest pageRequest=new PageRequest(page-1,pageSize);
//        if (!StringUtils.isEmpty(groupStatus)){
//            param.put("groupStatus",groupStatus);
//        }
//        if(!StringUtils.isEmpty(groupManagerPhone)){
//            param.put("groupManagerPhone",groupManagerPhone);
//        }
//        if(!StringUtils.isEmpty(groupName)){
//            param.put("groupName",groupName);
//        }
//        Page<Group> groupPage=groupService.findList(param,pageRequest);
//        return ResultVOUtil.success(groupPage);
//
//    }

}
