package com.smallow.service;

import com.smallow.entity.BadmintonGroup;

import java.util.List;

/**
 * Created by wanghuidong on 2017/8/30.
 */
public interface BadmintonGroupService {
    List<BadmintonGroup> findGroupInfoByOpenid(String openid);


    BadmintonGroup create(BadmintonGroup badmintonGroup);
}
