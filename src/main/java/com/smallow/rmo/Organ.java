package com.smallow.rmo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smallow.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by wanghuidong on 2018/4/24.
 */
@Data
public class Organ {
    /**
     * 机构标识
     */
    private Integer id;

    /**
     * 机构名称
     */
    private String organName;

    /**
     * 机构代码
     */
    private String organCode;

    /**
     * 父级机构代码
     */
    private String parentCode;

    /**
     * 机构简称
     */
    private String organShortName;

    /**
     * 机构描述
     */
    private String organDescription;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Timestamp createTime;


}
