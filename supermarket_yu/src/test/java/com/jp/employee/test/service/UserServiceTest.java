package com.jp.employee.test.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import com.chen.dao.User.UserDao;
import com.chen.dao.User.UserDaoImpl;
import com.chen.pojo.User;
import com.chen.service.User.UserServiceImpl;


public class UserServiceTest {
	//DAO层mock
	private final Connection conn = Mockito.mock(Connection.class);
	private final UserDao userDao = Mockito.mock(UserDaoImpl.class);
	//测试目标，new一个实例
	private final UserServiceImpl userServiceImpl = new UserServiceImpl();
	
	@Before
	public void before() {
		MockitoAnnotations.openMocks(this);
	}
	
	//login正常时
	@Test
	public void testLogin() throws SQLException{
		//1.datamock
		String userCode = "admin123";
		String passWord = "pass";
		User userForLogin = new User();
		userForLogin.setId(000);
		Mockito.when(userDao.getLoginInfo(conn, userCode)).thenReturn(userForLogin);
		
		//2.test实施
		userServiceImpl.login(userCode, passWord);
		
	}
}
