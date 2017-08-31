package com.smallow.service.impl;

import com.smallow.entity.BadmintonGroup;
import com.smallow.repository.BadmintonGroupRepository;
import com.smallow.service.BadmintonGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wanghuidong on 2017/8/30.
 */
@Service
public class BadmintonGroupServiceImpl implements BadmintonGroupService {

    @Autowired
    private BadmintonGroupRepository repository;

    @Override
    public List<BadmintonGroup> findGroupInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }


    @Override
    public BadmintonGroup create(BadmintonGroup badmintonGroup) {
        return repository.save(badmintonGroup);
    }
}
