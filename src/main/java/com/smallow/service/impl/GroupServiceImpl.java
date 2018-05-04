package com.smallow.service.impl;


import com.smallow.entity.Group;
import com.smallow.mapper.GroupMapper;
import com.smallow.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * Created by wanghuidong on 2017/8/30.
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    @Transactional
    public void save(Group group) {
        if(group.getGroupId()!=null){
            groupMapper.update(group);
        }else{
            groupMapper.insert(group);
        }
    }

    @Override
    public Page<Group> findList(Map<String, Object> param, Pageable pageable) {
        param.put("offset",pageable.getOffset());
        param.put("pageSize",pageable.getPageSize());
        List<Group> list=groupMapper.findList(param);
        long totalCount=groupMapper.count(param);
        return new PageImpl<Group>(list,pageable,totalCount);
    }

    @Override
    public Group findOne(Integer groupId) {
        return groupMapper.findOne(groupId);
    }
}
