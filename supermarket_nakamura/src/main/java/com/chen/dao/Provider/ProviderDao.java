package com.chen.dao.Provider;/*

 */

import com.chen.pojo.Provider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ProviderDao {
    //根据サプライヤコード 或 サプライヤ名称 查询サプライヤ总数
    public abstract int getProviderCounts(Connection conn,String queryProCode,String queryProName)throws SQLException;

    //查询サプライヤ数据列表
    public abstract List<Provider> getProviderList(Connection conn,String ProCode,String ProName,int currentPageNo, int pageSize)throws SQLException;

    //添加サプライヤ的方法
    public abstract boolean addProvider(Connection conn,Provider provider)throws SQLException;

    //删除サプライヤ的方法
    public abstract boolean deleteProvider(Connection conn,int providerId)throws SQLException;

    //根据サプライヤid查询サプライヤ信息的方法
    public abstract Provider findById(Connection conn,int providerId)throws SQLException;

    //修改サプライヤ信息方法
    public abstract boolean modifyProvider(Connection conn,int id,Provider provider)throws SQLException;
}
