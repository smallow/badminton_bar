package com.smallow.repository;

import com.smallow.entity.BadmintonGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by wanghuidong on 2017/8/22.
 */
public interface BadmintonGroupRepository extends JpaRepository<BadmintonGroup, Integer> {
    List<BadmintonGroup> findByOpenid(String openid);
}
