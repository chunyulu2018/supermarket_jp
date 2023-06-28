package com.chen.service.Role;/*

 */

import com.chen.dao.BaseDao;
import com.chen.dao.Role.RoleDao;
import com.chen.dao.Role.RoleDaoImpl;
import com.chen.pojo.Role;
//import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RoleServiceImpl implements RoleService {
    //业务层调用持久层
    private RoleDao roleDao = null;
    public RoleServiceImpl(){
        this.roleDao =new RoleDaoImpl();
    }
    @Override
    public List<Role> getRoleList()  {
        Connection conn = null;
        List<Role> roleList = null;
        try {
            //获取数据库连接
            conn = BaseDao.getConnection();
            roleList = roleDao.getRoleList(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(conn,null,null);
            return roleList;
        }
    }
    
    /*
    @Test
    public void testGetRoleList(){
        RoleServiceImpl roleService = new RoleServiceImpl();
        List<Role> roleList = roleService.getRoleList();
        for (Role role : roleList) {
            System.out.println(role.getRoleName());
        }
    }
    */
}
