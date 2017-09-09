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
    private String groupIcon;
    private Integer groupCheck;
    private String openid;
}
