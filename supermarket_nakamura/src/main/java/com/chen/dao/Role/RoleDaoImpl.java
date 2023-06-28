package com.chen.dao.Role;/*

 */

import com.chen.dao.BaseDao;
import com.chen.pojo.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao {
    //ロールリストを取得
    @Override
    public List<Role> getRoleList(Connection conn) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs  = null;
        ArrayList<Role> roles = new ArrayList<>();
        if(conn != null){
            String sql  = "select * from smbms_role";
            Object[] params = {};
            rs = BaseDao.executeQuery(conn, sql, pstm, params, rs);
            while(rs.next()){
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setRoleName(rs.getString("roleName"));
                role.setRoleCode(rs.getString("roleCode"));
                roles.add(role);
            }
        }
        BaseDao.closeResource(null,pstm,rs);
        return roles;
    }
}
