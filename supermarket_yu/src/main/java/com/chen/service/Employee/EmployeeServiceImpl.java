package com.chen.service.Employee;

import com.alibaba.fastjson.JSONArray;
import com.chen.dao.BaseDao;
import com.chen.dao.Employee.EmployeeDao;
import com.chen.dao.Employee.EmployeeDaoImpl;
import com.chen.pojo.Employee;

import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    //业务层调用dao层的
    private EmployeeDao employeeDao;
    public EmployeeServiceImpl(){
    	employeeDao =new EmployeeDaoImpl();
    }
	
	//查询记录数
	@Override
	public int getEmployeeCounts(String userName, int rank) {
		Connection conn = null;
        int employeeCounts = 0;
        try {
            //获取连接
            conn = BaseDao.getConnection();
            //执行sql语句
            employeeCounts = employeeDao.getEmployeeCounts(conn, userName, rank);
            
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(conn,null,null);
        }
            return employeeCounts;
        
	}
	//查询记录数 userCode
	public int getEmpCounts(String userCode) {
		Connection conn = null;
        int empCounts = 0;
        try {
            //获取连接
            conn = BaseDao.getConnection();
            //执行sql语句
            empCounts = employeeDao.getEmpCounts(conn, userCode);
            
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(conn,null,null);
        }
            return empCounts;
        
		
	}
	//根据条件 查询社員情報列表
	@Override
	public List<Employee> getEmployeeList(String QueryUserName, int QueryRank, int currentPageNo, int pageSize) {
		 	Connection conn = null;
	        List<Employee> employeeList = null;

	        try {
	            //获取数据库连接
	            conn = BaseDao.getConnection();
	            //调dao层的到employeeList
	            employeeList = employeeDao.getEmployeeList(conn, QueryUserName, QueryRank, currentPageNo, pageSize);
	        }catch (SQLException e){
	            e.printStackTrace();
	        }finally {
	            BaseDao.closeResource(conn,null,null);
	            //返回查询的社員
	            return employeeList;
	        }
	}
	//增加员工
	@Override
	public boolean addEmployee(Employee employee) {
	    Connection conn = null;
	    boolean flag = false;
	    try {
	        //获取数据库连接
	        conn = BaseDao.getConnection();
	        //开启JDBC事务管理
	        conn.setAutoCommit(false);
	        //Service层调用dao层的方法添加用户
	        int updateRows = employeeDao.addEmployee(conn, employee);
	        conn.commit();
	        if(updateRows > 0){
	            flag = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        conn.rollback();
	    }finally {
	        //释放连接
	        BaseDao.closeResource(conn,null,null);
	        return flag;
	    }
	}
	
	//删除
	@Override
    public boolean deleteEmployee(int userId) {
        boolean flag = false;
        Connection conn = null;
        try {
            //获取数据库连接
            conn = BaseDao.getConnection();
            //开启事务
            conn.setAutoCommit(false);
            flag = employeeDao.deleteEmployee(conn, userId);
            //提交事务
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
            //事务回滚
            conn.rollback();
        }finally {
            //释放连接
            BaseDao.closeResource(conn,null,null);
            return flag;
        }
    }
	
	//id查询
	@Override
    public Employee findById(int userId) {
        Employee employee = null;
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            conn.setAutoCommit(false);
            employee = employeeDao.findById(conn, userId);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }finally {
            BaseDao.closeResource(conn,null,null);
            return employee;
        }
    }
	
	//修改
	@Override
    public boolean modify(int id,Employee employee) {
        Connection conn = null;
        boolean flag = false;
        try {
            conn = BaseDao.getConnection();
            //开启事务
            conn.setAutoCommit(false);
            flag = employeeDao.modify(conn, id,employee);
            //提交事务
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            //事务回滚
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            //释放资源
            BaseDao.closeResource(conn,null,null);
            return flag;
        }
    }
	
}


