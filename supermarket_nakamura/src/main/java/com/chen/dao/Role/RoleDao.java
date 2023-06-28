package com.chen.dao.Role;/*

 */

import com.chen.pojo.Role;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
public interface RoleDao {
    //获取角色列表
    public abstract List<Role> getRoleList(Connection conn)throws SQLException;
}
