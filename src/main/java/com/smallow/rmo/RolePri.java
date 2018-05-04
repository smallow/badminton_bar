package com.smallow.rmo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smallow.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 角色权限
 * Created by wanghuidong on 2018/4/24.
 */
@Data
public class RolePri {

    /**
     * 角色权限标识
     */
    private Integer id;

    /**
     * 角色代码
     */
    private String roleCode;

    /**
     * 权限代码
     */
    private String priCode;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Timestamp createTime;

}
