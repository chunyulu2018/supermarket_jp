package com.chen.dao.Provider;/*
 *

 */

import com.chen.dao.BaseDao;
import com.chen.pojo.Provider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProviderDaoImpl implements ProviderDao {
    @Override
    public int getProviderCounts(Connection conn, String queryProCode, String queryProName) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int ProviderCounts = 0;
        if(conn != null){
            //SQLの作成
            StringBuffer sql = new StringBuffer();
            sql.append("select count(1) as ProviderCount from smbms_provider");

            //パラメータ
            ArrayList<Object> paramsList = new ArrayList<>();

            //パラメータ2つの場合、ANDを追加
            if(queryProCode != "" && queryProName != ""){
                sql.append(" where ProCode = ? and ProName like ?");
                paramsList.add(queryProCode);
                paramsList.add("%"+queryProName+"%");
            }else if(queryProCode !="" || queryProName != ""){
                sql.append(" where");
                if(queryProCode != ""){
                    sql.append(" ProCode = ?");
                    paramsList.add(queryProCode);
                }
                if(queryProName != ""){
                    sql.append(" ProName like ?");
                    paramsList.add("%"+queryProName+"%");
                }
            }
            //sql作成完了
            System.out.println("Test ProviderDaoImpl -> "+sql.toString());
            //パラメータ変換
            Object[] params = paramsList.toArray();
            //sql実行
            rs = BaseDao.executeQuery(conn, sql.toString(), pstm, params,rs);
            //結果をループ
            while (rs.next()){
                ProviderCounts = rs.getInt("ProviderCount");
            }
            //リソース解放
            BaseDao.closeResource(null,pstm,rs);
        }
        System.out.println("ProviderDaoImpl-> ProviderCounts："+ProviderCounts);
            return ProviderCounts;
    }

    @Override
    public List<Provider> getProviderList(Connection conn, String ProCode, String ProName,int currentPageNo, int pageSize) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs  = null;
        List<Provider> providers = new ArrayList<Provider>();
        if(conn != null){
            //SQL文の作成
            StringBuffer sql = new StringBuffer();
            sql.append("select * from smbms_provider");
            //建一个参数列表 注意这里泛型使用Object
            List<Object> paramsList = new ArrayList<>();
            //SQL语句拼接  参数赋值
            // 如果两个都不为空 sql需要拼接and
            if(ProCode != "" && ProName != ""){
                sql.append(" where ProCode = ? and ProName like ?");
                paramsList.add(ProCode);
                paramsList.add("%"+ProName+"%");
            }else if(ProCode != "" || ProName != ""){
                sql.append(" where");
                if(ProCode != ""){
                    sql.append(" ProCode = ?");
                    paramsList.add(ProCode);
                }
                if(ProName != ""){
                    sql.append(" ProName like ?");
                    paramsList.add("%"+ProName+"%");
                }
            }
            //在数据库中 分页使用limit startIndex,pageSize 总数
            //当前页 = (当前页-1)*页面大小
            sql.append(" order by creationDate DESC limit ?,?");
            currentPageNo = (currentPageNo-1)*pageSize;
            paramsList.add(currentPageNo);
            paramsList.add(pageSize);

            //将参数列表进行转换
            Object[] params = paramsList.toArray();
        System.out.println("Test getProviderList ->  "+sql);
            //执行sql
            rs = BaseDao.executeQuery(conn, sql.toString(), pstm, params,rs);
            //遍历结果集 封装到一个サプライヤ中
            while (rs.next()){
                Provider provider = new Provider();
                provider.setId(rs.getInt("id"));
                provider.setProCode(rs.getString("proCode"));
                provider.setProName(rs.getString("proName"));
                provider.setProDesc(rs.getString("proDesc"));
                provider.setProContact(rs.getString("proContact"));
                provider.setProPhone(rs.getString("proPhone"));
                provider.setProAddress(rs.getString("proAddress"));
                provider.setProFax(rs.getString("proFax"));
                provider.setCreatedBy(rs.getInt("createdBy"));
                provider.setCreationDate(rs.getTimestamp("creationDate"));
                provider.setModifyDate(rs.getTimestamp("modifyDate"));
                provider.setModifyBy(rs.getInt("modifyBy"));
                //将此サプライヤ信息添加至 列表
                providers.add(provider);
            }
            //释放资源
            BaseDao.closeResource(null,pstm,rs);

        }
            return providers;
    }

    //添加サプライヤ的方法
    @Override
    public boolean addProvider(Connection conn, Provider provider) throws SQLException {
        PreparedStatement pstm = null;
        boolean flag = false;
        if(conn != null){
            //如果连接数据库成功 编写sql语句
            String sql = "insert into smbms_provider (proCode,proName,proContact,proPhone,proAddress,proFax,proDesc,createdBy,creationDate)values(?,?,?,?,?,?,?,?,?)";
            Object[] params ={provider.getProCode(),provider.getProName(),provider.getProContact(),provider.getProPhone(),provider.getProAddress(),provider.getProFax(),provider.getProDesc(),provider.getCreatedBy(),provider.getCreationDate()};
            //执行sql语句
            if(BaseDao.execute(conn, sql, pstm, params)>0){
                flag = true;
            }
            //释放资源
            BaseDao.closeResource(null,pstm,null);
        }
            return flag;
    }

    //删除サプライヤ的方法
    @Override
    public boolean deleteProvider(Connection conn, int providerId) throws SQLException {
        PreparedStatement pstm = null;
        boolean flag = false;
        //如果连接不为空
        if(conn != null){
            //写sql
            String sql = "delete from smbms_provider where id = ?";
            //参数列表
            Object[] params = {providerId};
            //执行sql
            int updateRows = BaseDao.execute(conn, sql, pstm, params);
            if(updateRows > 0){
                flag = true;
            }
            //释放资源
            BaseDao.closeResource(null,pstm,null);
        }
            return flag;
    }

    //根据サプライヤid查询サプライヤ信息的方法
    @Override
    public Provider findById(Connection conn, int providerId) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Provider provider = null;
        //如果连接不为空
        if(conn != null){
            //编写sql语句
            String sql = "select * from smbms_provider where id = ?";
            //参数列表
            Object[] params = {providerId};
            //执行sql
            rs = BaseDao.executeQuery(conn, sql, pstm, params, rs);
            //遍历结果集 封装
            if(rs.next()){
                provider = new Provider();
                provider.setProCode(rs.getString("proCode"));;
                provider.setProName(rs.getString("proName"));
                provider.setProDesc(rs.getString("proDesc"));
                provider.setProContact(rs.getString("proContact"));
                provider.setProPhone(rs.getString("proPhone"));
                provider.setProAddress(rs.getString("proAddress"));
                provider.setProFax(rs.getString("proFax"));
                provider.setCreatedBy(rs.getInt("createdBy"));
                provider.setCreationDate(rs.getTimestamp("creationDate"));
                provider.setModifyDate(rs.getTimestamp("modifyDate"));
                provider.setModifyBy(rs.getInt("modifyBy"));
            }
            //释放资源
            BaseDao.closeResource(null,pstm,rs);
        }
            return provider;
    }
    //修改サプライヤ信息方法
    @Override
    public boolean modifyProvider(Connection conn, int id,Provider provider) throws SQLException {
        PreparedStatement pstm = null;
        boolean flag = false;
        if(conn != null){
            //编写sql语句
            String sql = "update smbms_provider set proCode = ?,proName = ?,proDesc = ?,proContact = ?,proPhone = ?,proAddress = ?,proFax = ?,modifyDate = ?,modifyBy = ? where id = ?";
            Object[] params = {provider.getProCode(),provider.getProName(),provider.getProDesc(),provider.getProContact(),provider.getProPhone(),provider.getProAddress(),provider.getProFax(),provider.getModifyDate(),provider.getModifyBy(),id};
            int updateRows = BaseDao.execute(conn, sql, pstm, params);
            if(updateRows > 0){
                flag = true;
            }
            BaseDao.closeResource(null,pstm,null);
        }
            return flag;
    }
}
