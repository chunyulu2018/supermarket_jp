package com.chen.dao.Emp;/*

 */

import com.chen.dao.BaseDao;
import com.chen.pojo.Emp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//登录 判断 的实现类
public class EmpDaoImpl implements EmpDao {
    @Override
    //得到要登录的用户信息
    public Emp getLoginInfo(Connection conn, String empCode) throws SQLException {
    	System.out.print("処理開始....");
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Emp emp = null;

        //如果连数据库都没连接就无需判断了
        if(conn!=null){
            //编写sql语句预编译sql
            String sql = "select * from smbms_emp where empCode = ?";
            //存放参数
            Object[] params = {empCode};
            //使用预处理对象调用  オペレーション数据库的公共类 的执行 sql查询语句
            rs = BaseDao.executeQuery(conn, sql, preparedStatement, params,rs);
            //遍历结果集  封装到一个用户中
            if(rs.next()){
            	/*
                emp = new Emp();
                emp.setId(rs.getInt("id"));
                emp.setEmpCode(rs.getString("empCode"));
                emp.setEmpName(rs.getString("empName"));
                emp.setEmpPassword(rs.getString("empPassword"));
                emp.setGender(rs.getInt("gender"));
                emp.setBirthday(rs.getDate("birthday"));
                emp.setPhone(rs.getString("phone"));
                emp.setAddress(rs.getString("address"));
                emp.setEmpRole(rs.getInt("empRole"));
                emp.setCreatedBy(rs.getInt("createdBy"));
                emp.setCreateDate(rs.getTimestamp("creationDate"));
                emp.setModifyBy(rs.getInt("modifyBy"));
                emp.setModifyDate(rs.getTimestamp("modifyDate"));
                */
            }
            //调用  オペレーション数据库的公共类 的执行 释放资源
            BaseDao.closeResource(null,preparedStatement,rs);
        }
        //返回一个用户
        return emp;
    }
   //修改当前用户密码
    @Override
    public int updatePassword(Connection conn, int id, String newPsd) throws SQLException {
        PreparedStatement pstm = null;
        int result = 0;
        if (conn != null) {
            String sql = "update smbms_emp set empPassword = ? where id = ?";
            Object params[] = {newPsd,id};
            result = BaseDao.execute(conn, sql, pstm, params);
            BaseDao.closeResource(null,pstm,null);
        }
        return result;
    }


    //根据用户名 或 角色 查询用户总数
    @Override
    public int getEmpCounts(Connection conn, String empname, int empRole) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        if(conn!=null){
            //SQL语句因为联合多表查询 所以需要拼接
            StringBuffer sql = new StringBuffer();
            //默认两表联合  查询总条数
            sql.append("select count(1) as count from smbms_emp u,smbms_rank r where u.rank = r.id");

            //建一个集合来存储参数
            ArrayList<Object> list = new ArrayList<>();
            if(empname!=null){
                sql.append(" and u.empName like ?");
                list.add("%"+empname+"%");//默认下标为0
            }
            if(empRole>0 & empRole<4){
                sql.append(" and u.empRole = ?");
                list.add(empRole);//默认下标为1
            }
            //把list转换为数组
            Object[] arrays = list.toArray();
            System.out.println("拼接的sql语句："+sql.toString());
            //执行sql语句
            rs = BaseDao.executeQuery(conn, sql.toString(), pstm, arrays, rs);
            //遍历结果集
            if(rs.next()){
                //从结果集中获取数量
                count = rs.getInt("count");
            }
            //最终关闭资源连接
            BaseDao.closeResource(null,pstm,rs);
        }
        return count;
    }

    //根据条件 查询 获取用户列表 emplist
    @Override
    public List<Emp> getEmpList(Connection conn, String empname, int empRole, int currentPageNo, int pageSize) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Emp> empList = new ArrayList<Emp>();
        if(conn!=null){
            //SQL语句因为联合多表查询 所以需要拼接
            StringBuffer sql = new StringBuffer();
            //默认两表联合  查询总条数
            sql.append("select u.*,r.rankName as empRoleName from smbms_emp u,smbms_rank r where u.rank = r.id");

            //建一个集合来存储参数
            List<Object> list = new ArrayList<>();
            if(empname!=null){
                sql.append(" and u.empName like ?");
                list.add("%"+empname+"%");//默认下标为0
            }
            if(empRole>0 & empRole<4){
                sql.append(" and u.empRole = ?");
                list.add(empRole);//默认下标为1
            }
            //在数据库中 分页使用limit startIndex,pageSize 总数
            //当前页 = (当前页-1)*页面大小
            sql.append(" order by u.creationDate DESC limit ?,?");
            currentPageNo = (currentPageNo-1)*pageSize;
            list.add(currentPageNo);
            list.add(pageSize);

            Object[] params = list.toArray();
        System.out.println("getEmpList的语句"+sql.toString());
            //执行sql
            rs = BaseDao.executeQuery(conn, sql.toString(), pstm, params, rs);
            while (rs.next()){
                Emp emp = new Emp();
                
                emp.setId(rs.getInt("id"));
                emp.setEmpCode(rs.getString("empCode"));
                emp.setEmpName(rs.getString("empName"));
                emp.setRank(rs.getString("rank"));
                emp.setAge(rs.getInt("age"));
                emp.setGender(rs.getInt("gender"));
                emp.setPhone(rs.getString("phone"));
                emp.setMail(rs.getString("mail"));
                emp.setIDCard(rs.getString("IDCard"));
                emp.setPicture(rs.getString("picture"));
                emp.setAddress(rs.getString("address"));
                emp.setEmpRoleName(rs.getString("empRoleName"));
                
                //将此サプライヤ信息添加至 列表
                empList.add(emp);
            }
            BaseDao.closeResource(null,pstm,rs);
        }
            return empList;
        }
    //用户管理模块中的 子模块—— 添加用户
    public  int addEmp(Connection conn,Emp emp)throws SQLException{
        PreparedStatement pstm = null;
        int updateRows = 0;
        if(conn != null){
            String sql = "INSERT INTO smbms_emp (empCode,empName,`rank`,age,gender,phone,mail,IDCard,picture,address) VALUES (?,?,?,?,?,?,?,?,?,?)";
            
        	int genderFlg = 0;
            if(emp.getGender().equals("男性")) {
            	genderFlg = 1;
            }
            else if(emp.getGender().equals("女性")) {
            	genderFlg = 2;
            }
            
            Object[] params = {emp.getEmpCode(),emp.getEmpName(),Integer.parseInt(emp.getRank()),emp.getAge(),genderFlg,emp.getPhone(),emp.getMail(),emp.getIDCard(),emp.getPicture(),emp.getAddress()};
            //执行sql 返回执行结果(成功的语句数量)
            updateRows= BaseDao.execute(conn,sql,pstm,params);
            //释放资源
            BaseDao.closeResource(null,pstm,null);
        }
        return updateRows;
    }

    //用户管理模块中的子模块 —— 删除用户
    @Override
    public boolean deleteEmp(Connection conn, int empCode)throws SQLException {
        PreparedStatement pstm = null;
        boolean flag = false;
        if(conn != null){
            String sql = "delete from smbms_emp where id = ?";
            Object[] params = {empCode};
            //执行sql 返回执行结果(成功的语句数量)
            int updateRows= BaseDao.execute(conn,sql,pstm,params);
            if(updateRows>0){
                flag = true;
            }
            //释放资源
            BaseDao.closeResource(null,pstm,null);
        }
            return flag;
    }
    //根据用户 id查询用户信息
    @Override
    public Emp findById(Connection conn, int empId) throws SQLException {
        Emp emp = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if(conn != null){
            String sql = "select u.*,r.rankName as empRoleName from smbms_emp u,smbms_rank r where u.id = ? and u.rank = r.id";
            Object[] params ={empId};
            rs = BaseDao.executeQuery(conn, sql, pstm, params, rs);
            if(rs.next()){
            	
                emp = new Emp();
                emp.setId(rs.getInt("id"));
                emp.setEmpCode(rs.getString("empCode"));
				emp.setEmpName(rs.getString("empName"));
				emp.setRank(rs.getString("rank"));
				emp.setAge(rs.getInt("age"));
				emp.setGender(rs.getInt("gender"));
				emp.setPhone(rs.getString("phone"));
				emp.setMail(rs.getString("mail"));
				emp.setIDCard(rs.getString("IDCard"));
				emp.setPicture(rs.getString("picture"));
				emp.setAddress(rs.getString("address"));
				emp.setCreatedBy(rs.getInt("createdBy"));
				emp.setCreationDate(rs.getTimestamp("creationDate"));
				emp.setModifyDate(rs.getTimestamp("modifyDate"));
				emp.setModifyBy(rs.getInt("modifyBy"));
            }
            //释放资源
            BaseDao.closeResource(null,pstm,rs);
        }
            return emp;
    }

    //用户管理模块中的子模块 —— 更改用户信息
    @Override
    public boolean modify(Connection conn, int id,Emp emp) throws SQLException {
        boolean flag = false;
        PreparedStatement pstm = null;
        if(conn != null){
            //编写sql语句
        	String sql = "update smbms_emp set empCode = ?,empName = ?,`rank` = ?,age = ?,gender = ?,phone = ?,mail = ?,IDCard = ?,picture = ?,address = ? where id = ?";
            
        	int genderFlg = 0;
            if(emp.getGender().equals("男性")) {
            	genderFlg = 1;
            }
            else if(emp.getGender().equals("女性")) {
            	genderFlg = 2;
            }
        	
            Object[] params = {emp.getEmpCode(),emp.getEmpName(),Integer.parseInt(emp.getRank()),emp.getAge(),genderFlg,emp.getPhone(),emp.getMail(),emp.getIDCard(),emp.getPicture(),emp.getAddress(),id};
            
            //执行sql语句
            int updateRows = BaseDao.execute(conn, sql, pstm, params);
            if(updateRows>0){
                flag = true;
            }
            //释放连接
            BaseDao.closeResource(null,pstm,null);
        }
            return flag;
    }
}
