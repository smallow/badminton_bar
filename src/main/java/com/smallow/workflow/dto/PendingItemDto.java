package com.smallow.workflow.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smallow.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by wanghuidong on 2018/5/3.
 */
@Data
public class PendingItemDto<T> {
    /**
     * 数据库标识
     */
    private String id;
    /**
     * 业务数据
     */
    private T work;

    /**
     * 待办标题
     */
    private String title;

    /**
     * 业务类型代码
     */
    private String businessCode;
    /**
     * 业务类型名称
     */
    private String businessName;

    /**
     * 流程ID
     */
    private Integer approvalProcessId;

    /**
     * 流程步骤ID
     */
    private Integer approvalProcessStepId;

    /**
     * 待办所属人ID
     */
    private Integer userId;

    /**
     * 创建时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Timestamp createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Timestamp updateTime;
}
