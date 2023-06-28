package com.chen.service.Provider;/*

 */

import com.chen.pojo.Provider;

import java.sql.SQLException;
import java.util.List;

public interface ProviderService {
    //根据サプライヤコード 或 サプライヤ名称 查询サプライヤ总数
    public abstract int getProviderCounts(String queryProCode,String queryProName)throws SQLException;

    //查询サプライヤ数据列表
    public abstract List<Provider> getProviderList(String ProCode, String ProName, int currentPageNo, int pageSize)throws SQLException;

    //添加サプライヤ的方法
    public abstract boolean addProvider(Provider provider)throws SQLException;

    //删除サプライヤ的方法
    public abstract boolean deleteProvider(int providerId)throws SQLException;

    //根据サプライヤid查询サプライヤ信息的方法
    public abstract Provider findById(int providerId)throws SQLException;

   //修改サプライヤ信息方法
    public abstract boolean modifyProvider(int id,Provider provider)throws SQLException;
}
