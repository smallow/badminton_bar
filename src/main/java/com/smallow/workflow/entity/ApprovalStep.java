package com.smallow.workflow.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smallow.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 审批步骤
 * Created by wanghuidong on 2018/4/24.
 */
@Data
public class ApprovalStep {
    /**
     * 步骤id
     */
    private Integer id;
    /**
     * 步骤名称
     */
    private String name;

    /**
     * 步骤代码
     */
    private String code;
    /**
     * 步骤序号
     */
    private Integer order;

    /**
     * 流程ID
     */
    private Integer approvalProcessId;

    /**
     * 能处理的该步骤的角色代码
     */
    private String roleCode;

    /**
     * 创建时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Timestamp createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Timestamp updateTime;
}
