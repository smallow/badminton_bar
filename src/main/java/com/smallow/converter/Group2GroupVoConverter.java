package com.smallow.converter;

import com.smallow.VO.GroupVo;
import com.smallow.entity.Group;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wanghuidong on 2017/9/9.
 */
public class Group2GroupVoConverter {
    public static GroupVo convert(Group group) {
        GroupVo groupVo = new GroupVo();
        BeanUtils.copyProperties(group, groupVo);
        return groupVo;
    }

    public static List<GroupVo> convert(List<Group> groupList) {
        return groupList.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
