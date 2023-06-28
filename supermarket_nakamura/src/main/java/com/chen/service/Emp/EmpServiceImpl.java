package com.chen.service.Emp;



import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.chen.dao.BaseDao;
import com.chen.dao.Emp.EmpDao;
import com.chen.dao.Emp.EmpDaoImpl;
import com.chen.pojo.Emp;

//用户登录的业务层实现类
public class EmpServiceImpl implements EmpService {
    //业务层肯定是调用dao层的
    private EmpDao empDao;
    public EmpServiceImpl(){
        empDao =new EmpDaoImpl();
    }

    @Override
    //(String empCode, String passWord)两个参数对应是的首页传来的值
    //用户登录
    public Emp login(String empCode, String passWord) {
        Connection conn = null;
        Emp emp = null;

        try {
            //调用 dao层オペレーション数据库的公共类方法 获取数据库的连接
            conn = BaseDao.getConnection();
            //得到连接后 开始查询 通过业务层调用具体的数据库オペレーション
            emp = empDao.getLoginInfo(conn, empCode);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            BaseDao.closeResource(conn,null,null);
        }
        return emp;
    }

    //修改密码
    @Override
    public boolean updatePassword(int id, String passWord) {
        boolean flag = false;
        Connection conn = null;
        try {
            //获取连接
            conn = BaseDao.getConnection();
            //调用dao层 执行更新オペレーション
            int i = empDao.updatePassword(conn, id, passWord);
            if (i > 0) {
                flag = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(conn,null,null);
            return flag;
        }
    }

    //用户管理——查询记录数
    @Override
    public int getEmpCounts(String empname, int empRole) {
        Connection conn = null;
        int empCounts = 0;
        try {
            //获取连接
            conn = BaseDao.getConnection();
            //执行sql语句
            empCounts = empDao.getEmpCounts(conn, empname, empRole);

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(conn,null,null);
            return empCounts;
        }
    }
    //根据条件 查询用户列表
    @Override
    public List<Emp> getEmpList(String QueryEmpName, int QueryEmpRole, int currentPageNo, int pageSize) {
        Connection conn = null;
        List<Emp> empList = null;

        try {
            //获取数据库连接
            conn = BaseDao.getConnection();
            //调dao层的到empList
            empList = empDao.getEmpList(conn, QueryEmpName, QueryEmpRole, currentPageNo, pageSize);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(conn,null,null);
            //返回查询的用户
            return empList;
        }
    }

    //用户管理模块中的 子模块—— 添加用户
    @Override
    public boolean addEmp(Emp emp) {
        Connection conn = null;
        boolean flag = false;
        try {
            //获取数据库连接
            conn = BaseDao.getConnection();
            //开启JDBC事务管理
            conn.setAutoCommit(false);
            //Service层调用dao层的方法添加用户
            int updateRows = empDao.addEmp(conn, emp);
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

    //用户管理模块中的子模块 —— 删除用户
    @Override
    public boolean deleteEmp(int empId) {
        boolean flag = false;
        Connection conn = null;
        try {
            //获取数据库连接
            conn = BaseDao.getConnection();
            //开启事务
            conn.setAutoCommit(false);
            flag = empDao.deleteEmp(conn, empId);
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

    //根据id查询用户信息
    @Override
    public Emp findById(int empId) {
        Emp emp = null;
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            conn.setAutoCommit(false);
            emp = empDao.findById(conn, empId);
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
            return emp;
        }
    }

    //用户管理模块中的子模块 —— 更改用户信息
    @Override
    public boolean modify(int id,Emp emp) {
        Connection conn = null;
        boolean flag = false;
        try {
            conn = BaseDao.getConnection();
            //开启事务
            conn.setAutoCommit(false);
            flag = empDao.modify(conn, id,emp);
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
    /*
    @Test
    public void Test(){
        EmpServiceImpl us = new EmpServiceImpl();
        Emp admin = us.login("CSNZ", "1");
        System.out.println("管理员admin的密码:"+admin!=null?true:false);
    }
    @Test
    public void TestEmpCounts(){
        EmpServiceImpl us = new EmpServiceImpl();
        int counts = us.getEmpCounts(null, 0);
        System.out.println(counts);
    }
    */
    @Test
    public void TestGetEmpList(){
        EmpServiceImpl empService = new EmpServiceImpl();
        List<Emp> empList = empService.getEmpList("", 0, 1, 5);
        for (Emp emp : empList) {
            System.out.println(emp);
        }
    }
}
