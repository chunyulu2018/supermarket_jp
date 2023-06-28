package com.chen.service.Emp;

import com.chen.pojo.Emp;

import java.util.List;


public interface EmpService {
    //用户登录
    public abstract Emp login(String empCode,String passWord);

    //根据用户ID修改密码
    public abstract boolean updatePassword(int id,String passWord);
    //用户管理——查询记录数
    public abstract int getEmpCounts(String empname,int empRole);
    //根据条件 查询用户列表
    public abstract List<Emp> getEmpList(String QueryEmpName,int QueryEmpRole,int currentPageNo,int pageSize);
    //用户管理模块中的 子模块—— 添加用户
    public abstract boolean addEmp(Emp emp);
    //用户管理模块中的子模块 —— 删除用户
    public abstract boolean deleteEmp(int empId);
    //根据id查询用户信息
    public abstract Emp findById(int empId);
    //用户管理模块中的子模块 —— 更改用户信息
    public abstract boolean modify(int id,Emp emp);
}