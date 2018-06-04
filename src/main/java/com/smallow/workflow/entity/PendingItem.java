package com.smallow.workflow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smallow.enums.BadmintonGroupCheckEnum;
import com.smallow.utils.serializer.Date2LongSerializer;
import com.smallow.workflow.enums.PendingItemStatusEnum;
import com.smallow.workflow.utils.EnumUtil;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 待办事项
 * Created by wanghuidong on 2018/4/27.
 */
@Data
public class PendingItem {
    /**
     * 数据库标识
     */
    private String id;

    /**
     * 事务标识 (如一条请假记录，一条招聘记录)
     */
    private Integer workId;

    /**
     * 待办标题
     */
    private String title;

    /**
     * 业务类型代码
     */
    private String businessCode;

    /**
     * 待办所属人ID
     */
    private Integer userId;

    /**
     * 待办处理状态 0待处理 1已处理
     */
    private Integer status = PendingItemStatusEnum.PENDING.getCode();

    /**
     * 待办提交人ID
     */
    private Integer sendUserId;

    /**
     * 待办提交时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Timestamp sendTime;


    /**
     * 流程ID
     */
    private Integer approvalProcessId;

    /**
     * 步骤ID
     */
    private Integer approvalProcessStepId;


    @JsonIgnore
    public PendingItemStatusEnum getPendingItemStatusEnum() {
        return EnumUtil.getByCode(status, PendingItemStatusEnum.class);
    }

    /**
     * 创建时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Timestamp createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Timestamp updateTime;
}
