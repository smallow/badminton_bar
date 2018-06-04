package com.smallow.workflow.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smallow.utils.serializer.Date2LongSerializer;
import com.smallow.workflow.enums.WorkFlowDealTypeEnum;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by wanghuidong on 2018/5/8.
 */
@Data
public class WorkFlow {

    /**
     * 数据库标识
     */
    private String id;

    /**
     * 事务标识 (如一条请假记录，一条招聘记录)
     */
    private Integer workId;

    /**
     * 流程步骤处理人ID
     */
    private Integer userId;

    /**
     * 流程buzhou 处理类型  0未处理 1通过 2拒绝 3终止
     */
    private Integer dealType;

    /**
     * 处理意见
     */
    private String dealRemark;

    /**
     * 处理时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Timestamp dealTime;

    /**
     * 流程ID
     */
    private Integer approvalProcessId;

    /**
     * 步骤ID
     */
    private Integer approvalProcessStepId;



    /**
     * 创建时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Timestamp createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Timestamp updateTime;
}
