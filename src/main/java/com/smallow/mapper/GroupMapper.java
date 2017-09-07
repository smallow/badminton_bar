package com.smallow.mapper;

import com.smallow.entity.Group;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by wanghuidong on 2017/9/5.
 */
@Component(value = "groupMapper")
public interface GroupMapper {


    List<Group> findAll();

    Group finOne(Integer groupId);

    void insert(Group group);

    void deleteByPK(Integer groupId);


    List<Group> findList(Map<String,Object> map);

    long count(Map<String,Object> map);

}
