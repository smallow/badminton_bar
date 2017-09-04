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
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
        badmintonGroup.setGroupName("郑州羽飞扬俱乐部");
        badmintonGroup.setGroupIcon("http://img.mp.itc.cn/upload/20160909/475a465d06d3476e8adfcae3e6c89062_th.jpg");
        //badmintonGroup.setGroupIcon("http://avatar.csdn.net/A/9/2/1_xiakepan.jpg");
        badmintonGroup.setGroupManagerIdNumber("41130219840716051X");
        badmintonGroup.setGroupManagerName("王会东");
        badmintonGroup.setGroupManagerPhone("13603456869");
        badmintonGroup.setGroupCheck(BadmintonGroupCheckEnum.NEED.getCode());
        badmintonGroup.setGroupStatus(GroupStatusEnum.APPLY.getCode());
        badmintonGroup.setOpenid("oDUen0mqihpEtwribIeb_Muq5yzw");
        badmintonGroup.setGroupMemo("临时接管俱乐部222");
        int i=badmintonGroupService.save(badmintonGroup);
        Assert.assertNotEquals(0,i);
    }


    @Test
    public void findList() throws Exception {
        Group group=new Group();
        group.setGroupManagerPhone("13603456869");
        //group.setGroupName("郑州飞羽");
        List<Group> list=badmintonGroupService.findList(group);
        System.out.println(list);
    }

}