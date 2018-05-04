package com.smallow.rmo;

import lombok.Data;

/**
 * 用户角色
 * Created by wanghuidong on 2018/4/24.
 */
@Data
public class UserRole {

    /**
     * 角色权限标识
     */
    private Integer id;

    /**
     * 登录用户id
     */
    private Integer userId;

    /**
     * 角色代码
     */
    private String roleCode;
}
