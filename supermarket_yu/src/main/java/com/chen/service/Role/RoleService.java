package com.chen.service.Role;/*
 *
 *@create 2021-05-03-23:33
 */

import com.chen.pojo.Role;
import java.util.List;

public interface RoleService {
    //获取角色列表
    public abstract List<Role> getRoleList();
}
