package com.smallow.rmo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smallow.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 权限
 * Created by wanghuidong on 2018/4/24.
 */
@Data
public class Privilege {

    /**
     * 权限标识
     */
    private Integer id;

    /**
     * 权限名称
     */
    private String priName;

    /**
     * 权限代码
     */
    private String priCode;

    /**
     * 权限指向的菜单url
     */
    private String url;

    /**
     * 父级权限代码
     */
    private String parentPriCode;

    /**
     * 描述
     */
    private String description;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonSerialize(using = Date2LongSerializer.class)
    private Timestamp createTime;
}
