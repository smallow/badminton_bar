package com.smallow.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smallow.utils.serializer.Date2LongSerializer;
import com.smallow.utils.serializer.GroupCheckSerializer;
import com.smallow.utils.serializer.GroupStatusSerializer;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by wanghuidong on 2017/9/9.
 */
@Data
public class GroupVo {
    /**
     * 类目id.
     */
    @JsonProperty("groupId")
    private String groupId;

    /**
     * 群组名称
     */
    @JsonProperty("groupName")
    private String groupName;

    /**
     * 群组图标
     */
    @JsonProperty("groupIcon")
    private String groupIcon;

    /**
     * 群组介绍
     */
    @JsonProperty("groupMemo")
    private String groupMemo;

    /**
     * 群管理员名称
     */
    @JsonProperty("groupManagerName")
    private String groupManagerName;

    /**
     * 群管理员身份证号
     */
    @JsonProperty("groupManagerIdNumber")
    private String groupManagerIdNumber;

    /**
     * 群管理员手机号
     */
    @JsonProperty("groupManagerPhone")
    private String groupManagerPhone;

    /**
     * 进群审核标志
     */
    @JsonSerialize(using = GroupCheckSerializer.class)
    private Integer groupCheck;

    @JsonSerialize(using = GroupStatusSerializer.class)
    private Integer groupStatus;

    @JsonProperty("openid")
    private String openid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp updateTime;


}
