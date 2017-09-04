package com.smallow.mapper;


import com.smallow.entity.Group;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;
import java.util.List;


/**
 * Created by wanghuidong on 2017/8/31.
 */
@Component(value = "groupMapper")
public interface GroupMapper {


    @Insert("insert into badminton_group (group_name,group_icon,group_manager_name,group_manager_id_number,group_manager_phone,group_status,group_memo,group_check,openid)  values " +
            "(#{groupName},#{groupIcon},#{groupManagerName},#{groupManagerIdNumber},#{groupManagerPhone},#{groupStatus},#{groupMemo},#{groupCheck},#{openid}) ")
    //@Options(useGeneratedKeys = true, keyProperty = "groupId")
    int insertByObject(Group group);

    @SelectProvider(type = GroupSqlProvider.class,method ="findList")
    @Results({
            @Result(property = "groupName", column = "group_name"),
            @Result(property = "groupManagerName", column = "group_manager_name"),
            @Result(property = "groupManagerPhone", column = "group_manager_phone"),
    })
    List<Group> findList(Group group);

}
