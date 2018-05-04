package com.smallow.rmo.service;

import com.smallow.rmo.Role;

import java.util.List;

/**
 * Created by wanghuidong on 2018/4/27.
 */
public interface RoleService {
    /**
     * 创建角色
     *
     * @param role
     * @return
     */
    Role save(Role role);

    /**
     * 查询单个角色
     *
     * @param roleCode
     * @return
     */
    Role findOne(String roleCode);

    Role findOne(Integer roleId);

    /**
     * 查询所有角色
     *
     * @return
     */
    List<Role> findAll();
}
