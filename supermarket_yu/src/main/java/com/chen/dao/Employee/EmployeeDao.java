package com.chen.dao.Employee;

import com.chen.pojo.Employee;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {
	//根据ユーザ名、会員ランク　查询社員情報条数
	public abstract int getEmployeeCounts(Connection conn,String userName,int rank)throws SQLException;
	//根据 ユーザ名、会員ランク　查询社員情報列表
	public abstract List<Employee> getEmployeeList(Connection conn,String userName,int rank,int currentPageNo,int pageSize)throws SQLException;
	
	//根据usercode查询社員情報条数
	public abstract int getEmpCounts(Connection conn, String userCode)throws SQLException;
	
	//子模块—— 添加员工
    public abstract int addEmployee(Connection conn,Employee employee)throws SQLException;

    //子模块 —— 删除员工
    public abstract boolean deleteEmployee(Connection conn,int userId)throws SQLException;

    //根据id 查询员工信息
    public abstract Employee findById(Connection conn,int userId)throws SQLException;

    //子模块 —— 更改员工信息
    public abstract boolean modify(Connection conn,int id,Employee employee)throws SQLException;
}
