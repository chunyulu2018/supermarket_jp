package com.chen.dao.Bill;/*
 *
オペレーション数据库的

 */

import com.chen.pojo.Bill;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface BillDao {
    //根据 商品名称、サプライヤid、支払の有無 查询订单总数
    public abstract int getBillCount(Connection conn,String queryProductName,int queryProviderId,int queryIsPayment)throws  SQLException;
    //根据 商品名称、サプライヤid、支払の有無 查询订单列表
    public abstract List<Bill> getBillList(Connection conn,String queryProductName,int queryProviderId,int queryIsPayment, int currentPageNo, int pageSize) throws SQLException;
    //添加订单
    public abstract boolean addBill(Connection conn,Bill bill)throws SQLException;
    //删除订单
    public abstract boolean deleteBill(Connection conn,int billId)throws SQLException;
    //根据订单id 获取订单信息
    public abstract Bill findByBillId(Connection conn,int billId)throws SQLException;
    //修改订单信息
    public abstract boolean modifyBill(Connection conn,int billId,Bill bill)throws SQLException;
}
