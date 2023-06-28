package com.chen.dao.Emp;/*

 */

import com.chen.pojo.Emp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//登录 判断 的接口
public interface EmpDao {
    //得到要登录的用户信息
    public abstract Emp getLoginInfo(Connection conn,String empCode) throws SQLException;

    //修改密码
    public abstract int updatePassword(Connection conn,int id,String newPsd)throws SQLException;

    //根据用户名 或 角色 查询用户总数
    public abstract int getEmpCounts(Connection conn,String empname,int empRole)throws SQLException;

    //根据条件 查询 获取用户列表 empList
    public abstract List<Emp> getEmpList(Connection conn,String empname,int empRole,int currentPageNo,int pageSize)throws SQLException;

    //用户管理模块中的 子模块—— 添加用户
    public abstract int addEmp(Connection conn,Emp emp)throws SQLException;

    //用户管理模块中的子模块 —— 删除用户
    public abstract boolean deleteEmp(Connection conn,int empId)throws SQLException;

    //根据用户id 查询用户信息
    public abstract Emp findById(Connection conn,int empId)throws SQLException;

    //用户管理模块中的子模块 —— 更改用户信息
    public abstract boolean modify(Connection conn,int id,Emp emp)throws SQLException;
}