package com.smallow.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smallow.enums.BadmintonGroupCheckEnum;
import com.smallow.enums.GroupStatusEnum;
import com.smallow.utils.EnumUtil;
import com.smallow.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by wanghuidong on 2017/8/21.
 * 群组信息
 */
@Data
public class Group {

    /**
     * 类目id.
     */
    private String groupId;

    /**
     * 群组名称
     */
    private String groupName;

    /**
     * 群组图标
     */
    private String groupIcon;

    /**
     * 群组介绍
     */
    private String groupMemo;

    /**
     * 群管理员名称
     */
    private String groupManagerName;

    /**
     * 群管理员身份证号
     */
    private String groupManagerIdNumber;

    /**
     * 群管理员手机号
     */
    private String groupManagerPhone;

    /**
     * 进群审核标志
     */
    private Integer groupCheck = BadmintonGroupCheckEnum.NEED.getCode();



    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonSerialize(using = Date2LongSerializer.class)
    private Timestamp createTime;



    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonSerialize(using = Date2LongSerializer.class)
    private Timestamp updateTime;

    private Integer groupStatus= GroupStatusEnum.APPLY.getCode();

    private String openid;

    @JsonIgnore
    public BadmintonGroupCheckEnum getBacmintonGroupCheckEnum() {
        return EnumUtil.getByCode(groupCheck, BadmintonGroupCheckEnum.class);
    }
    @JsonIgnore
    public GroupStatusEnum getGroupStatusEnum(){
        return EnumUtil.getByCode(groupStatus,GroupStatusEnum.class);
    }

}
