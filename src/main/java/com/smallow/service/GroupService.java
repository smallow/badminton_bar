package com.smallow.service;


import com.smallow.entity.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * Created by wanghuidong on 2017/8/30.
 */
public interface GroupService {


    Group findOne(Integer groupId);

    void save(Group group);
    /**
     * 综合查询 根据群状态、管理员手机号、openid、群名称
     */
    Page<Group> findList(Map<String,Object> param, Pageable pageable);


}
