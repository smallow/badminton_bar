package com.smallow.rmo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smallow.rmo.enums.EmployeeSexEnum;
import com.smallow.utils.EnumUtil;
import com.smallow.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 一般用户
 * Created by wanghuidong on 2018/4/24.
 */
@Data
public class Employee {

    /**
     * 用户标识
     */
    private Integer id;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 性别
     */
    private Integer sex;

    /**
     * 年龄
     */

    private Integer age;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 固定电话
     */
    private String telephone;

    /**
     * 机构ID
     */
    private Integer orgId;

    /**
     *
     */
    private Integer userId;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonSerialize(using = Date2LongSerializer.class)
    private Timestamp createTime;

    @JsonIgnore
    public EmployeeSexEnum getEmployeeSexEnum() {
        return EnumUtil.getByCode(sex, EmployeeSexEnum.class);
    }


}
