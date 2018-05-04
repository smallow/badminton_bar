package com.smallow.workflow.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smallow.utils.serializer.Date2LongSerializer;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * 审批流程
 * Created by wanghuidong on 2018/4/24.
 */
@Data
public class ApprovalProcess {
    /**
     * 流程标识
     */
    private Integer id;
    /**
     * 流程名称
     */
    private String name;
    /**
     * 业务类型代码
     */
    private String businessCode;
    /**
     * 业务类型名称
     */
    private String businessName;

    /**
     * 流程状态 0启用 1废弃
     */
    private Integer status;

    /**
     * 创建时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Timestamp createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Timestamp updateTime;
    /**
     * 流程创建用户标识
     */
    private Integer userId;

    /**
     * 备注
     */
    private String memo;
}
