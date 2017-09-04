package com.smallow.mapper;

import com.smallow.entity.Group;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

/**
 * Created by wanghuidong on 2017/9/1.
 */
public class GroupSqlProvider {


    public String findList(Group group){
        StringBuffer tmpBuffer=new StringBuffer("");
        return new SQL(){{
            SELECT("group_name,group_manager_name,group_manager_phone");
            FROM("badminton_group");

            if(!StringUtils.isEmpty(group.getGroupName())){
                tmpBuffer.append("group_name=#{groupName}");
            }
            if(!StringUtils.isEmpty(group.getGroupManagerPhone())){
                tmpBuffer.append("group_manager_phone=#{groupManagerPhone}");
            }
            WHERE(tmpBuffer.toString());
            ORDER_BY("create_time desc");
        }}.toString();
    }

}
