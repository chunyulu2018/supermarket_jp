package com.jp.employee.test.dao;


import org.junit.Test;

import com.chen.dao.Employee.EmployeeDaoImpl;
import com.chen.pojo.Employee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDaoTest {

    // 查询用户数测试
    @Test
    public void testGetEmployeeCounts() throws SQLException {
        // 定义数据库连接的参数           
        String url = "jdbc:mysql://localhost:3306/smbms";
        String user = "root";
        String password = "root";

        // 创建一个真实的连接对象
        Connection conn = DriverManager.getConnection(url, user, password);

        // 创建一个员工dao对象
        EmployeeDaoImpl employeeDaoImpl = new EmployeeDaoImpl();

        // 用一些参数来调用getEmployeeCounts方法  
        int count = employeeDaoImpl.getEmployeeCounts(conn, "Alice", 2);

        // 验证count是否为1
        assertEquals(1, count);

        // 关闭资源连接
        conn.close();
    }
    
    //查询用户列表测试
    @Test
    public void testGetEmployeeList() throws SQLException {
        // 定义数据库连接的参数
    	String url = "jdbc:mysql://localhost:3306/smbms";
        String user = "root";
        String password = "root";

        // 创建一个真实的连接对象
        Connection conn = DriverManager.getConnection(url, user, password);

        // 创建一个员工dao对象
        EmployeeDaoImpl employeeDaoImpl = new EmployeeDaoImpl();

        // 定义一些测试用例，覆盖不同的参数组合
		/* String[] userNames = {null, "Alice", "Bob"}; */
        String userName = "Alic";
      /*  int[] ranks = {0, 2, 2};*/
        int rank = 2;
        int currentPageNo = 1;
        int pageSize = 100;
        // 调用getEmployeeList方法，获取返回的列表
        List<Employee> employeeList = employeeDaoImpl.getEmployeeList(conn, userName, rank, currentPageNo, pageSize);
        
        assertEquals(employeeList.get(0).getUserName(), "Alic");
        assertEquals(employeeList.get(0).getUserCode(), "SX_DEMO011");
        assertEquals(employeeList.get(1).getUserName(), "Alice"); 
        

        // 关闭资源连接
        conn.close();
    }

    
}
