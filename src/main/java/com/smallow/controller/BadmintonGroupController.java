package com.smallow.controller;

import com.smallow.VO.GroupVo;
import com.smallow.VO.ResultVO;
import com.smallow.converter.Group2GroupVoConverter;
import com.smallow.entity.Group;
import com.smallow.exception.BadmintonException;
import com.smallow.form.GroupForm;
import com.smallow.service.GroupService;
import com.smallow.utils.KeyUtil;
import com.smallow.utils.ResultVOUtil;
import freemarker.template.utility.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
    public ModelAndView list() {
        return new ModelAndView("admin/group/list");
    }
    @GetMapping("/add")
    public ModelAndView add(@RequestParam(value = "groupId", required = false) Integer groupId,
                            Map<String, Object> map) {
        if(groupId!=null){
            Group group=groupService.findOne(groupId);
            map.put("group",group);
        }else{
            map.put("qr_code", KeyUtil.genUniqueKey());
        }
        return new ModelAndView("admin/group/add",map);
    }

    //    @GetMapping("/list")
//    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
//                             @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
//                             @RequestParam(value = "groupStatus", defaultValue = "0") Integer groupStatus,
//                             @RequestParam(value = "groupManagerPhone", defaultValue = "") String groupManagerPhone,
//                             @RequestParam(value = "groupName", defaultValue = "") String groupName,
//                             @RequestParam(value = "groupManagerName", defaultValue = "") String groupManagerName,
//                             Map<String, Object> map
//                             ) {
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
//        map.put("groupPage", groupPage);
//        map.put("currentPage", page);
//        map.put("pageSize", pageSize);
//        return new ModelAndView("admin/group/list");
//    }
    @GetMapping("/list2")
    @ResponseBody
    public ResultVO<List<Group>> list2() {
        Map<String, Object> param = new HashMap<>();
        PageRequest pageRequest = new PageRequest(0, 10);
        Page<Group> groupPage = groupService.findList(param, pageRequest);
        return ResultVOUtil.success(groupPage.getContent());
    }

    @PostMapping("/list")
    @ResponseBody
    public ResultVO list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                               @RequestParam(value = "groupStatus", defaultValue = "0") Integer groupStatus,
                               @RequestParam(value = "groupManagerPhone", defaultValue = "") String groupManagerPhone,
                               @RequestParam(value = "groupName", defaultValue = "") String groupName,
                               @RequestParam(value = "groupManagerName", defaultValue = "") String groupManagerName) {
        Map<String, Object> param = new HashMap<>();
        PageRequest pageRequest = new PageRequest(page - 1, pageSize);
        if (!StringUtils.isEmpty(groupStatus)) {
            param.put("groupStatus", groupStatus);
        }
        if (!StringUtils.isEmpty(groupManagerPhone)) {
            param.put("groupManagerPhone", groupManagerPhone);
        }
        if (!StringUtils.isEmpty(groupName)) {
            param.put("groupName", groupName);
        }
        Page<Group> groupPage = groupService.findList(param, pageRequest);
        List<GroupVo> groupVoList = new ArrayList<>();
        groupVoList = Group2GroupVoConverter.convert(groupPage.getContent());
        Map<String,Object> returnData=new HashMap<>();
        returnData.put("content",groupVoList);
        returnData.put("currentPage",page);
        returnData.put("pageSize",pageSize);
        returnData.put("totalPage",groupPage.getTotalPages());
        returnData.put("totalElements",groupPage.getTotalElements());
        return ResultVOUtil.success(returnData);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid GroupForm form,
                         BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/badminton/admin/group/list");
            return new ModelAndView("common/error", map);
        }

        Group group=new Group();
        try{
            if(form.getGroupId()!=null){
                group=groupService.findOne(form.getGroupId());
            }
            BeanUtils.copyProperties(form, group);
            if(group.getOpenid()==null){
                group.setOpenid("");
            }
            groupService.save(group);
        }catch (BadmintonException e){
            map.put("msg", e.getMessage());
            map.put("url", "/badminton/admin/group/list");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/badminton/admin/group/list");
        return new ModelAndView("common/success", map);

    }

}
