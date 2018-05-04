package com.smallow.rmo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smallow.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 登录用户
 * Created by wanghuidong on 2018/4/24.
 */
@Data
public class User {

    /**
     * 用户标识
     */
    private Integer id;
    /**
     * 登录用户代码
     */
    private String loginCode;
    /**
     * 密码
     */
    private String pwd;

    /**
     * 一般用户关联标识
     */
    private Integer employeeId;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Timestamp createTime;
}
