package com.smallow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smallow.enums.BadmintonGroupCheckEnum;
import com.smallow.enums.GroupStatusEnum;
import com.smallow.utils.EnumUtil;
import com.smallow.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by wanghuidong on 2017/8/21.
 * 群组信息
 */
@Data
public class Group {

    /**
     * id.
     */
    private String groupId;

    /**
     * 群组名称
     */
    private String groupName;

    /**
     * 群组规模(成员数量)
     */
    private Integer groupScale;

    /**
     * 活动场馆
     */
    private String groupArena;
    /**
     * 活动场馆标识
     */
    private String groupArenaCode;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 城市区划代码
     */
    private String cityCode;

    /**
     * 所在省份
     */
    private String province;

    /**
     * 所在省份区划代码
     */
    private String provinceCode;

    /**
     * 所在城市区域
     */
    private String area;

    /**
     * 所在城市区域代码
     */
    private String areaCode;

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

    private Integer groupStatus = GroupStatusEnum.APPLY.getCode();

    private String openid;

    @JsonIgnore
    public BadmintonGroupCheckEnum getBacmintonGroupCheckEnum() {
        return EnumUtil.getByCode(groupCheck, BadmintonGroupCheckEnum.class);
    }

    @JsonIgnore
    public GroupStatusEnum getGroupStatusEnum() {
        return EnumUtil.getByCode(groupStatus, GroupStatusEnum.class);
    }

}
