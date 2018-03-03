package com.smallow.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by wanghuidong on 2017/9/9.
 */
@Data
public class GroupForm {
    private Integer groupId;
    @NotEmpty(message = "群管理员姓名必填")
    private String groupManagerName;
    @NotEmpty(message = "群管理员手机号必填")
    private String groupManagerPhone;
    @NotEmpty(message = "群管理员身份证号必填")
    private String groupManagerIdNumber;
    @NotEmpty(message = "群名称必填")
    private String groupName;
    @NotEmpty(message = "群组规模必填")
    private String groupScale;
    @NotEmpty(message = "活动场馆必填")
    private String groupArenaCode;
    @NotEmpty(message = "活动场馆必填")
    private String groupArena;


    @NotEmpty(message = "省份必填")
    private String province;
    @NotEmpty(message = "省份必填")
    private String provinceCode;

    @NotEmpty(message = "城市必填")
    private String city;
    @NotEmpty(message = "城市必填")
    private String cityCode;


    @NotEmpty(message = "区域必填")
    private String area;
    @NotEmpty(message = "区域必填")
    private String areaCode;



    private String groupIcon;
    private Integer groupCheck;
    private String openid;
}
