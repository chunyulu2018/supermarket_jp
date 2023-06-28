package com.jp.employee.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chen.pojo.Employee;
import com.chen.service.Employee.EmployeeService;
import com.chen.service.Employee.EmployeeServiceImpl;
import com.chen.servlet.Employee.EmployeeServlet;
import com.chen.util.Constants;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class EmployeeServletTest {
	
	 private HttpServletRequest req;
	 private HttpServletResponse resp;
	 private EmployeeServlet employeeServlet = new EmployeeServlet();
	 private EmployeeServiceImpl employeeService;
	 private PrintWriter writer;
	 
	 @Before
		public void before() {
		 //使用MockitoAnnotations.openMocks(this)方法来初始化被@Mock或@InjectMocks注解的字段
		    MockitoAnnotations.openMocks(this);
		    //或者手动创建Mock对象
		    req = Mockito.mock(HttpServletRequest.class);
		    resp = Mockito.mock(HttpServletResponse.class);
		    employeeService = Mockito.mock(EmployeeServiceImpl.class);
		    writer = Mockito.mock(PrintWriter.class);
		}
	 
	 @Test
	    public void testIfExist() throws IOException {
		 	
	        //模拟req.getParameter("userCode")返回"123"
	        Mockito.when(req.getParameter("userCode")).thenReturn("123");
	        //模拟employeeService.getEmpCounts("123")返回1
	        Mockito.when(employeeService.getEmpCounts("123")).thenReturn(1);
	        //模拟resp.getWriter()返回writer
	        Mockito.when(resp.getWriter()).thenReturn(writer);

	        //调用被测方法
	        employeeServlet.ifExist(req, resp);

	        //验证resp.setContentType("application/json")被调用一次
	        Mockito.verify(resp).setContentType("application/json");
	        //验证writer.write(Mockito.anyString())被调用一次
	        Mockito.verify(writer).write(Mockito.anyString());
	        //验证writer.flush()被调用一次
	        Mockito.verify(writer).flush();
	        //验证writer.close()被调用一次
	        Mockito.verify(writer).close();
	    }
	}
