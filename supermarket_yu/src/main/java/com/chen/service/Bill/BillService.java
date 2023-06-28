package com.chen.service.Bill;/*
 *
业务层
 */

import com.chen.pojo.Bill;
import java.sql.SQLException;
import java.util.List;

public interface BillService {
    //根据 商品名称、サプライヤid、支払の有無 查询订单总数
    public abstract int getBillCount(String queryProductName, int queryProviderId, int queryIsPayment) throws SQLException;
    //根据 商品名称、サプライヤid、支払の有無 查询订单列表
    public abstract List<Bill> getBillList(String queryProductName, int queryProviderId, int queryIsPayment, int currentPageNo, int pageSize) throws SQLException;
    //添加订单
    public abstract boolean addBill(Bill bill);
    //删除订单
    public abstract boolean deleteBill(int billId);
    //根据订单id 获取订单信息
    public abstract Bill findByBillId(int billId)throws SQLException;
    //修改订单信息
    public abstract boolean modifyBill(int billId, Bill bill)throws SQLException;
}
