package com.smallow.service.impl;


import com.smallow.entity.Group;
import com.smallow.mapper.GroupMapper;
import com.smallow.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by wanghuidong on 2017/8/30.
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    @Transactional
    public int save(Group group) {
        return groupMapper.insertByObject(group);
    }

    @Override
    public List<Group> findList(Group group) {
        return groupMapper.findList(group);
    }
}
