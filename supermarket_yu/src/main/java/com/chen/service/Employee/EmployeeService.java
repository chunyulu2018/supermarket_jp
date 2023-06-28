package com.chen.service.Employee;

import com.chen.pojo.Employee;

import java.util.List;

public interface EmployeeService {
	//社員情報管理——查询记录数
    public abstract int getEmployeeCounts(String userName,int rank);
    //根据条件 查询社員情報列表
    public abstract List<Employee> getEmployeeList(String QueryUserName,int QueryRank,int currentPageNo,int pageSize);
    //查询记录数
    public abstract int getEmpCounts(String userCode);
    //添加
    public abstract boolean addEmployee(Employee employee);
    //删除
    public abstract boolean deleteEmployee(int userId);
    //根据id查询社員情報
    public abstract Employee findById(int userId);
    //修改
    public abstract boolean modify(int id,Employee employee);
}
