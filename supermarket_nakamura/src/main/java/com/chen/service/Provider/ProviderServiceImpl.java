package com.chen.service.Provider;/*

 */

import com.chen.dao.BaseDao;
import com.chen.dao.Provider.ProviderDao;
import com.chen.dao.Provider.ProviderDaoImpl;
import com.chen.pojo.Provider;
import java.sql.Connection;
import java.util.List;

public class ProviderServiceImpl implements ProviderService {
    //Service层调用dao层
    private ProviderDao providerDao;
    public ProviderServiceImpl(){
        providerDao = new ProviderDaoImpl();
    }

    //根据サプライヤコード 或 サプライヤ名称 查询サプライヤ总数
    @Override
    public int getProviderCounts(String queryProCode, String queryProName)  {
        Connection conn = null;
        int providerCounts = 0;
        try {
            conn = BaseDao.getConnection();
            conn.setAutoCommit(false);
            providerCounts = providerDao.getProviderCounts(conn, queryProCode, queryProName);
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally {
            BaseDao.closeResource(conn,null,null);
            return providerCounts;
        }
    }

    @Override
    public List<Provider> getProviderList(String ProCode, String ProName, int currentPageNo, int pageSize)  {
        List<Provider> providerList = null;
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            conn.setAutoCommit(false);
            providerList = providerDao.getProviderList(conn, ProCode, ProName, currentPageNo, pageSize);
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally {
            BaseDao.closeResource(conn,null,null);
            return providerList;
        }
    }
    //添加サプライヤ的方法
    @Override
    public boolean addProvider(Provider provider) {
        Connection conn = null;
        boolean flag = false;
        try {
            //获取数据库连接
            conn = BaseDao.getConnection();
            //开启事务
            conn.setAutoCommit(false);
            //执行方法
            flag = providerDao.addProvider(conn, provider);
            //提交事务
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
            //事务回滚
            conn.rollback();
        }finally {
            //释放资源
            BaseDao.closeResource(conn,null,null);
            return flag;
        }
    }

    //删除サプライヤ的方法
    @Override
    public boolean deleteProvider(int providerId) {
        Connection conn = null;
        boolean flag = false;
        try {
            conn = BaseDao.getConnection();
            conn.setAutoCommit(false);
            flag = providerDao.deleteProvider(conn, providerId);
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally {
            BaseDao.closeResource(conn,null,null);
            return flag;
        }
    }

    //根据サプライヤid查询サプライヤ信息的方法
    @Override
    public Provider findById(int providerId)  {
        Connection conn = null;
        Provider provider = null;
        try {
            conn = BaseDao.getConnection();
            conn.setAutoCommit(false);
            provider = providerDao.findById(conn, providerId);
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally {
            BaseDao.closeResource(conn,null,null);
            return provider;
        }
    }
    //修改サプライヤ信息方法
    @Override
    public boolean modifyProvider(int id, Provider provider)  {
        Connection conn  =  null;
        boolean flag = false;
        try {
            conn = BaseDao.getConnection();
            conn.setAutoCommit(false);
            flag = providerDao.modifyProvider(conn, id, provider);
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally {
            BaseDao.closeResource(conn,null,null);
            return flag;
        }
    }


}
