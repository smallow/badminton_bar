package com.smallow.rmo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smallow.utils.serializer.Date2LongSerializer;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * 角色
 * Created by wanghuidong on 2018/4/24.
 */
@Data
public class Role {

    /**
     * 角色标识
     */
    private Integer id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色代码
     */
    private String roleCode;
    /**
     * 角色描述
     */
    private String roleDescription;
    /**
     * 创建人标识
     */
    private Integer createUserId;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Timestamp createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Timestamp updateTime;
}
