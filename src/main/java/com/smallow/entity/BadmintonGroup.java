package com.smallow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smallow.enums.BadmintonGroupCheckEnum;
import com.smallow.enums.GroupStatusEnum;
import com.smallow.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by wanghuidong on 2017/8/21.
 * 群组信息
 */
@Entity
@Data
@DynamicUpdate
public class BadmintonGroup {

    /**
     * 类目id.
     */
    @Id
    @GeneratedValue
    private Integer groupId;

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


    private Date createTime;

    private Date updateTime;

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
