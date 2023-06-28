package com.jp.employee.test.service;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import com.chen.dao.BaseDao;
import com.chen.dao.Employee.EmployeeDao;
import com.chen.dao.Employee.EmployeeDaoImpl;
import com.chen.pojo.Employee;
import com.chen.service.Employee.EmployeeService;
import com.chen.service.Employee.EmployeeServiceImpl;


public class EmployeeServiceTest {
	//DAO层mock
	private final Connection conn = Mockito.mock(Connection.class);
	private final EmployeeDao employeeDao = Mockito.mock(EmployeeDao.class);
	//测试目标，new一个实例
	private final EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
	
	//第一个，测试查询记录数
	@Before
	public void before() {
		MockitoAnnotations.openMocks(this);
	}

	//计数正常时
	@Test
	public void testGetEmployeeCounts() throws SQLException {
		//1.datamock
		String userName = "测试者1";
		int rank = 2;
		Employee employeeForCounts = new Employee();
		employeeForCounts.setId(020);
		Mockito.when(employeeDao.getEmployeeCounts(conn, userName, rank)).thenReturn(1);
		//2.test实施
		employeeServiceImpl.getEmployeeCounts(userName, rank);
	}
	
	//第二个，记录正常时
	@Test
	public void testGetEmpCounts() throws SQLException{
		//1.datamock
		String userCode = "BJ000023";
		Employee empForCounts = new Employee();
		empForCounts.setId(021);

		Mockito.when(employeeDao.getEmpCounts(conn, userCode)).thenReturn(1);
		//2.test实施
		employeeServiceImpl.getEmpCounts(userCode);
	}
	
}
