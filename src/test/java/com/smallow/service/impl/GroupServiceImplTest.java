package com.smallow.service.impl;

import com.smallow.entity.Group;
import com.smallow.enums.BadmintonGroupCheckEnum;
import com.smallow.enums.GroupStatusEnum;
import com.smallow.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wanghuidong on 2017/8/31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GroupServiceImplTest {

    @Autowired
    private GroupServiceImpl badmintonGroupService;




    @Test
    public void save() throws Exception {
        Group badmintonGroup=new Group();
        //badmintonGroup.setGroupId(KeyUtil.genUniqueKey());
        badmintonGroup.setGroupName("羽你同行");
        badmintonGroup.setGroupIcon("http://img.mp.itc.cn/upload/20160909/475a465d06d3476e8adfcae3e6c89062_th.jpg");
        //badmintonGroup.setGroupIcon("http://avatar.csdn.net/A/9/2/1_xiakepan.jpg");
        badmintonGroup.setGroupManagerIdNumber("41130219840716051X");
        badmintonGroup.setGroupManagerName("王会东");
        badmintonGroup.setGroupManagerPhone("13603456869");
        badmintonGroup.setGroupCheck(BadmintonGroupCheckEnum.NEED.getCode());
        badmintonGroup.setGroupStatus(GroupStatusEnum.APPLY.getCode());
        badmintonGroup.setOpenid("oDUen0mqihpEtwribIeb_Muq5yzw");
        badmintonGroup.setGroupMemo("天天找你");
        badmintonGroupService.save(badmintonGroup);

    }


    @Test
    public void findList() throws Exception {
        PageRequest pageRequest=new PageRequest(0,2);
        Map<String,Object> map=new HashMap<>();
        map.put("groupManagerPhone","13603456869");
        Page<Group> page=badmintonGroupService.findList(map,pageRequest);
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
        System.out.println(page.hasPrevious());
        System.out.println(page.hasNext());
        System.out.println(page.isFirst());
        System.out.println(page.isLast());

    }

}