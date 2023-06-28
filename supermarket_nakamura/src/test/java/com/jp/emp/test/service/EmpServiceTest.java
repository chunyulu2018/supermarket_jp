package com.jp.emp.test.service;


import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.chen.dao.Emp.EmpDao;
import com.chen.dao.Emp.EmpDaoImpl;
import com.chen.pojo.Emp;
import com.chen.service.Emp.EmpServiceImpl;

public class EmpServiceTest {
	//DAO層のモック
	private final Connection conn = Mockito.mock(Connection.class);
	private final EmpDao empDao = Mockito.mock(EmpDaoImpl.class);
	//テストの目標、インスタンスをnew
	private final EmpServiceImpl empServiceImpl = new EmpServiceImpl();
	
	@Before
    public void before(){
		MockitoAnnotations.openMocks(this);
    }
	
	//ログイン正常の場合
	@Test
	public void testList() throws SQLException {
		
		/*
		//1.データモック
		String empCode="admin123";
		String passWord="pass";
		Emp empforLoin = new Emp();
		empforLoin.setId(000);
		*/
		//Mockito.when(empDao.getEmpList(conn, empCode)).thenReturn(empforLoin);
		Mockito.when(empDao.getEmpList(conn, "", 0, 1, 5));
		
		//2.テスト実施
		empServiceImpl.TestGetEmpList();;
		
		
	}
}