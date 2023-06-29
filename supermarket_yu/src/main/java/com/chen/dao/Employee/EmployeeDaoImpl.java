package com.chen.dao.Employee;

import com.chen.dao.BaseDao;
import com.chen.pojo.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class EmployeeDaoImpl implements EmployeeDao {
	
	 //根据用户名 或 角色 查询用户总数
    @Override
    public int getEmployeeCounts(Connection conn, String userName, int rank) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        if(conn!=null){
            //SQL语句因为联合多表查询 所以需要拼接
            StringBuffer sql = new StringBuffer();
            //默认两表联合  查询总条数
            sql.append("select count(1) as count from smbms_0001 u,smbms_rank r where u.rank = r.id");

            //建一个集合来存储参数
            ArrayList<Object> list = new ArrayList<>();
            if(userName!=null){
                sql.append(" and u.userName like ?");
                list.add("%"+userName+"%");//默认下标为0
            }
            if(rank>0 & rank<5){
                sql.append(" and u.rank = ?");
                list.add(rank);//默认下标为1
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
    //根据条件 查询 获取用户列表 employeelist
    @Override
	 public List<Employee> getEmployeeList(Connection conn, String userName, int rank,int currentPageNo, int pageSize) throws SQLException {
	        PreparedStatement pstm = null;
	        ResultSet rs  = null;
	        List<Employee> employeeList = new ArrayList<Employee>();
	        if(conn!=null){
	            //SQL语句因为联合多表查询 所以需要拼接
	            StringBuffer sql = new StringBuffer();
	            //默认两表联合  查询总条数
	            sql.append("select u.*,r.rankName from smbms_0001 u,smbms_rank r where u.rank = r.id");

	            //建一个集合来存储参数
	            List<Object> list = new ArrayList<>();
	            if(userName!=null){
	                sql.append(" and u.userName like ?");
	                list.add("%"+userName+"%");//默认下标为0
	            }
	            if(rank>0 & rank<5){
	                sql.append(" and u.rank = ?");
	                list.add(rank);//默认下标为1
	            }
	            //在数据库中 分页使用limit startIndex,pageSize 总数
	            //当前页 = (当前页-1)*页面大小
	            sql.append(" order by u.creationDate DESC limit ?,?");
	            currentPageNo = (currentPageNo-1)*pageSize;
	            list.add(currentPageNo);
	            list.add(pageSize);

	            Object[] params = list.toArray();
	            System.out.println("getEmployeeList的语句"+sql.toString());
	            //执行sql
	            rs = BaseDao.executeQuery(conn, sql.toString(), pstm, params,rs);
	            //遍历结果集 封装到一个サプライヤ中
	            while (rs.next()){
	            	Employee employee = new Employee();
	            	employee.setId(rs.getInt("id"));
	            	employee.setUserCode(rs.getString("userCode"));
	            	employee.setUserName(rs.getString("userName"));
	            	employee.setRank(rs.getInt("rank"));
	            	employee.setAge(rs.getInt("age"));
	            	employee.setGender(rs.getInt("gender"));
	            	employee.setPhone(rs.getString("phone"));
	            	employee.setMailAddress(rs.getString("mailAddress"));
	            	//employee.setMailAddress(rs.getString("phone"));
	            	employee.setAddress(rs.getString("address"));
	            	employee.setUserId(rs.getString("userId"));
	            

	                //将此サプライヤ信息添加至 列表
	            	employeeList.add(employee);
	            }

	            //System.out.print("データベースの接続を切断");

	            //System.out.print("資源解放");
	            //释放资源
	            BaseDao.closeResource(null,pstm,rs);
	        }
	            return employeeList;
	    }
    
    //增加
	@Override
	public  int addEmployee(Connection conn,Employee employee)throws SQLException{
        PreparedStatement pstm = null;
        int updateRows = 0;
        if(conn != null){
            String sql = "insert into smbms_0001 (userCode,userName,`rank`,age,gender,phone,mailAddress,address,userId,createdBy,creationDate)values(?,?,?,?,?,?,?,?,?,?,?)";
            Object[] params ={employee.getUserCode(),employee.getUserName(),employee.getRank(),employee.getAge(),employee.getGender(),employee.getPhone(),employee.getMailAddress(),employee.getAddress(),employee.getUserId(),employee.getCreatedBy(),employee.getCreateDate()};
            //执行sql 返回执行结果(成功的语句数量)
            updateRows= BaseDao.execute(conn,sql,pstm,params);
            //释放资源
            BaseDao.closeResource(null,pstm,null);
        }
        return updateRows;
    }
	
	//删除
	public boolean deleteEmployee(Connection conn, int userId)throws SQLException {
	    PreparedStatement pstm = null;
	    boolean flag = false;
	    if(conn != null){
	        String sql = "delete from smbms_0001 where id = ?";
	        Object[] params = {userId};
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
	
	//查询id
	@Override
	public Employee findById(Connection conn, int userId) throws SQLException {
	    Employee employee = null;
	    PreparedStatement pstm = null;
	    ResultSet rs = null;
	    if(conn != null){
	        String sql = "select u.*,r.rankName from smbms_0001 u,smbms_rank r where u.id = ? and u.rank = r.id";
	        Object[] params ={userId};
	        rs = BaseDao.executeQuery(conn, sql, pstm, params, rs);
	        if(rs.next()){
	            employee = new Employee();
	            employee.setId(rs.getInt("id"));
	            employee.setUserCode(rs.getString("userCode"));
	            employee.setUserName(rs.getString("userName"));
	            employee.setRank(rs.getInt("rank"));
	            employee.setAge(rs.getInt("age"));
	            employee.setGender(rs.getInt("gender"));            
	            employee.setPhone(rs.getString("phone"));
	            employee.setMailAddress(rs.getString("mailAddress"));
	            employee.setAddress(rs.getString("address"));
	            employee.setUserId(rs.getString("userId"));
	            employee.setCreatedBy(rs.getInt("createdBy"));
	            employee.setCreateDate(rs.getTimestamp("creationDate"));
	            employee.setModifyBy(rs.getInt("modifyBy"));
	            employee.setModifyDate(rs.getTimestamp("modifyDate"));
	            
	        }
	        //释放资源
	        System.out.print("test");
	        BaseDao.closeResource(null,pstm,rs);
	    }
	        return employee;
	}
	
	//修改
	@Override
	public boolean modify(Connection conn, int id,Employee employee) throws SQLException {
	    boolean flag = false;
	    PreparedStatement pstm = null;
	    if(conn != null){
	        //编写sql语句
	        String sql = "update smbms_0001 set userName = ?,`rank` = ?,age = ?,gender = ?,phone = ?,mailAddress = ?,address = ?,modifyBy = ?,modifyDate = ? where id = ?";
	        Object[] params = {employee.getUserName(),employee.getRank(),employee.getAge(),employee.getGender(),employee.getPhone(),employee.getMailAddress(),employee.getAddress(),employee.getModifyBy(),employee.getModifyDate(),id};
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
	
	////根据usercode查询社員情報条数
	@Override
	public int getEmpCounts(Connection conn, String userCode) throws SQLException {
		PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        if(conn!=null){
            //SQL语句因为联合多表查询 所以需要拼接
            StringBuffer sql = new StringBuffer();
            //默认两表联合  查询总条数
            sql.append("select count(1) as count from smbms_0001 where userCode=?");

            //建一个集合来存储参数
            ArrayList<Object> list = new ArrayList<>();
            list.add(userCode);
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
}
